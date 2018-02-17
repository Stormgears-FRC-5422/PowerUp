package org.stormgears.powerup.subsystems.sensors.stormnet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/*
 * We need to document the COMM protocol for the different devices
 * All devices should have a ping command
 * Ping:  1 byte request (0x01)
 * 		  1 byte argument (whatever)
 *        1 byte reply (bitwise OR of input argument (~in)
 */
public class StormNetSensor {
    private boolean m_debug = false;
	private boolean m_simulation = false;
	int m_numSensors = -1;
	String m_deviceString;
	private StormNetVoice m_voice;  // Handles the underlying communication channel
    byte[] m_command;

    // I don't understand why these are backwards, but there you have it.
    // I guess this follows the unix model of 0 exit code being OK
    public static final boolean STORMNET_SUCCESS = true;
    public static final boolean STORMNET_FAILURE = false;
	
	public StormNetSensor(StormNetVoice voice) {
		m_voice = voice;
	}

    // Accessors
    public void setSensorCount(int count) {
        m_numSensors = count;
    }

    public int getSensorCount() {
        return m_numSensors;
    }

    public void setDebug(boolean debug) {
        this.m_debug = debug;
    }

    public boolean getDebug() {
        return m_debug;
    }

    public void setSimulation(boolean simulation) {
        this.m_simulation = simulation;
    }

    public boolean getSimulation() {
        return m_simulation;
    }

    public boolean test() {
		return test(5);
	}

	public boolean test(int sleep) {
		try {
			// some basic tests
			log("Ping test returned " + Byte.toString(ping()));
			TimeUnit.SECONDS.sleep(sleep);
			log("Fast test returned " + (fast() ? "true" : "false") );
			TimeUnit.SECONDS.sleep(sleep);
			log("Slow test returned " + (slow() ? "true" : "false") );
			TimeUnit.SECONDS.sleep(sleep);
			log("Blink test returned " + (blink(2000) ? "true" : "false") );
			TimeUnit.SECONDS.sleep(sleep);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}


    /**
     * Turn a byte array into something more readable for display, using the characters
     * 0-9, A-F to construct the hex value of each byte.
     *
     * For example, if your byte array is {'A', 'B', 'C'} then
     * this function will return "414243".
     *
     * Thanks to StackOverflow for this implementation. It was originally called
     * byteToHexString()
     *
     * @param  in    a byte array
     * @return      the hex representation of the byte array as a String
     */
    private static String byteArrayToHexString(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    /**
     * Creates a byte array from a string containing the hex representation of each byte
     * using the characters 0-9, A-F to represent each byte.
     *
     * For example, if your input string is "414243" then the resulting byte array
     * will be {'A', 'B', 'C'}
     *
     * Thanks to StackOverflow for this implementation.
     *
     * @param  s	A hex string representation of a byte array
     * @return      The resulting byte array
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

//Logging
    /**
     * Optionally logs a message depending on whether the class level debug
     * member is set to true. If the debug member is set to false this function
     * does nothing.
     *
     * @param  message	A message string to print to System.out.
     * @see this#log
     */
    protected void debug(String message) {
        if (m_debug)
            log(message);
    }

    /**
     * Unconditionally logs a message.
     *
     * @param  message	A message string to print to System.out.
     */
    protected void log(String message) {
        System.out.println("id " + m_deviceString + ":" + message);
    }

// low-level i/o routines	
	/**
	 * A low-level routine that executes a transaction using the appropriate voice.
	 *
	 * Adds additional logging and creates a simulation mode to allow bytes to be 
	 * returned even if nothing is connected. Simulation mode always sends back bytes 
	 * receiveSize bytes from 0 to (receiveSize-1).
	 * 
	 * All StormNetSensor command calls eventually use this transaction method.
	 *
	 * Performs debug-level logging in debug mode.
	 * 
	 * @param  dataToSend	Buffer of data to send from master to slave to initiate the transaction.
	 * @param  sendSize		Number of bytes to send.
	 * @param  dataReceived	Pre-allocated buffer to receive data sent from slave to end the transaction.
	 * @param  receiveSize	Number of bytes to read from I2C bus.
	 * @see    edu.wpi.first.wpilibj.I2C
	 */	    
    public boolean transaction(byte[] dataToSend, int sendSize,	byte[] dataReceived, int receiveSize) {
        boolean result;

        // Simulation mode allows us to pretend without real sensors
        // We could make this more complicated if needed - not sure how valuable this will be
        if (!m_simulation) {
			result = m_voice.transaction(dataToSend, sendSize, dataReceived, receiveSize);
            debug("Result of transaction: " + result + (result == STORMNET_SUCCESS ? " (Success)" : " (Failure)") );
            debug("bytes returned: " + byteArrayToHexString(dataReceived));
        }
        else {
            for(byte i=0; i< receiveSize; i++)
                dataReceived[i] = i;
            result = STORMNET_SUCCESS;
        }

        return result;
    }

    /**
     * A convenience function to implement a simple commmand. Callers send a byte[] command and
     * read back an expected reply. The command may or may not have an effect on the device.
     *
     * Performs debug-level logging in debug mode.
     *
     * @param  command 		Command string to send
     * @param  commandName	Friendly name of command for debug logging.
     * @param  expect       bytes expected on return.
     * @return true or false depending on whether the received bytes match expected bytes.
     */
    protected boolean basicCommand(byte[] command, String commandName, byte[] expect) {
        debug(commandName);

        byte[] receive = new byte[expect.length];

		debug(commandName + " sending " + byteArrayToHexString(command));
        boolean result = transaction(command, command.length, receive, receive.length);

        // The transaction is successful only if the transaction succeeds and the ping response is what we expect
        // note that this flips the sense of the result to something sensible.
        result = Arrays.equals(receive, expect);
        debug("Overall " + commandName + " result = " + result);
        return result;
    }

    /**
     * A convenience function to implement a simple commmand. Callers send a byte[] command
	 * and read back enough bytes to fill the receiveBuffer provided.
     *
     * The command may or may not have an effect on the device.
     *
     * Performs debug-level logging in debug mode.
     *
     * @param  command 		Command string to send
     * @param  commandName	Friendly name of command for debug logging.
     * @param  receiveBuffer Bytes read from the slave by the master.
     * @return Result of command. True for STORMNET_SUCCESS (note the flip in sense)
     * @see this#transaction
     */
    protected boolean fetchCommand(byte[] command, String commandName, byte[] receiveBuffer) {
        debug(commandName);
        debug(commandName + " sending " + byteArrayToHexString(command));
        boolean result = transaction(command, command.length, receiveBuffer, receiveBuffer.length);

        // The transaction is successful only if the transaction succeeds and the ping response is what we expect
        debug("Overall " + commandName + " result = " + result);
        return result;
    }

    /**
     * A convenience function to implement a simple fetch commmand.
     * Callers send a String command and read back enough bytes to fill the
     * byteArray provided.
     *
     * The command may or may not have an effect on the device.
     *
     * Performs debug-level logging in debug mode.
     *
     * @param  command 		Command string to send
     * @param  commandName	Friendly name of command for debug logging.
     * @param  byteArray    Values read from the slave by the master.
     * @return Result of command. True for STORMNET_SUCCESS (note the flip in sense)
     * @see this#fetchCommand
     */
    protected boolean fetchBytes(String command, String commandName, byte[] byteArray){
        // fetching bytes doesn't require additional processing
        return fetchCommand(command.getBytes(), commandName, byteArray);
    }

    /**
     * A convenience function to implement a simple fetch commmand.
     * Callers send a String command and read back enough bytes to fill the
     * shortArray provided.
     *
     * The command may or may not have an effect on the device.
     *
     * Performs debug-level logging in debug mode.
     *
     * @param  command 		Command string to send
     * @param  commandName	Friendly name of command for debug logging.
     * @param  shortArray   Values read from the slave by the master.
     * @return Result of command. True for STORMNET_SUCCESS (note the flip in sense)
     * @see this#fetchCommand
     */
    protected boolean fetchShorts(String command, String commandName, short[] shortArray){
        byte[] receiveBuffer = new byte[shortArray.length * Short.BYTES];
        boolean result = fetchCommand(command.getBytes(), commandName, receiveBuffer);

        ByteBuffer buffer = ByteBuffer.wrap(receiveBuffer);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        for (int i=0; i<shortArray.length; i++) {
            shortArray[i] = buffer.getShort();
            debug("short value: " + shortArray[i]);
        }

        return result;
    }

    /**
     * A convenience function to implement a simple fetch commmand.
     * Callers send a String command and read back enough bytes to fill the
     * intArray provided.
     *
     * The command may or may not have an effect on the device.
     *
     * Performs debug-level logging in debug mode.
     *
     * @param  command 		Command string to send
     * @param  commandName	Friendly name of command for debug logging.
     * @param  intArray   Values read from the slave by the master.
     * @return Result of command. True for STORMNET_SUCCESS (note the flip in sense)
     * @see this#fetchCommand
     */
    protected boolean fetchInts(String command, String commandName, int[] intArray){
        byte[] receiveBuffer = new byte[intArray.length * Integer.BYTES];
        boolean result = fetchCommand(command.getBytes(), commandName, receiveBuffer);

        ByteBuffer buffer = ByteBuffer.wrap(receiveBuffer);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        for (int i=0; i<intArray.length; i++) {
            intArray[i] = buffer.getInt();
            debug("int value: " + intArray[i]);
        }

        return result;
    }

    /**
     * A convenience function to implement a simple fetch commmand.
     * Callers send a String command and read back enough bytes to fill the
     * longArray provided.
     *
     * The command may or may not have an effect on the device.
     *
     * Performs debug-level logging in debug mode.
     *
     * @param  command 		Command string to send
     * @param  commandName	Friendly name of command for debug logging.
     * @param  longArray   Values read from the slave by the master.
     * @return Result of command. True for STORMNET_SUCCESS (note the flip in sense)
     * @see this#fetchCommand
     */
    protected boolean fetchLongs(String command, String commandName, long[] longArray){
        byte[] receiveBuffer = new byte[longArray.length * Long.BYTES];
        boolean result = fetchCommand(command.getBytes(), commandName, receiveBuffer);

        ByteBuffer buffer = ByteBuffer.wrap(receiveBuffer);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        for (int i=0; i<longArray.length; i++) {
            longArray[i] = buffer.getLong();
            debug("long value: " + longArray[i]);
        }

        return result;
    }

    /**
     * A convenience function to implement a simple fetch commmand.
     * Callers send a String command and read back enough bytes to fill the
     * floatArray provided.
     *
     * The command may or may not have an effect on the device.
     *
     * Performs debug-level logging in debug mode.
     *
     * @param  command 		Command string to send
     * @param  commandName	Friendly name of command for debug logging.
     * @param  floatArray   Values read from the slave by the master.
     * @return Result of command. True for STORMNET_SUCCESS (note the flip in sense)
     * @see this#fetchCommand
     */
    protected boolean fetchFloats(String command, String commandName, float[] floatArray){
        byte[] receiveBuffer = new byte[floatArray.length * Float.BYTES];
        boolean result = fetchCommand(command.getBytes(), commandName, receiveBuffer);

        ByteBuffer buffer = ByteBuffer.wrap(receiveBuffer);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        for (int i=0; i<floatArray.length; i++) {
            floatArray[i] = buffer.getFloat();
            debug("float value: " + floatArray[i]);
        }

        return result;
    }

    /**
     * A convenience function to implement a simple fetch commmand.
     * Callers send a String command and read back enough bytes to fill the
     * doubleArray provided.
     *
     * The command may or may not have an effect on the device.
     *
     * Performs debug-level logging in debug mode.
     *
     * @param  command 		Command string to send
     * @param  commandName	Friendly name of command for debug logging.
     * @param  doubleArray   Values read from the slave by the master.
     * @return Result of command. True for STORMNET_SUCCESS (note the flip in sense)
     * @see this#fetchCommand
     */
    protected boolean fetchDoubles(String command, String commandName, double[] doubleArray){
        byte[] receiveBuffer = new byte[doubleArray.length * Double.BYTES];
        boolean result = fetchCommand(command.getBytes(), commandName, receiveBuffer);

        ByteBuffer buffer = ByteBuffer.wrap(receiveBuffer);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        for (int i=0; i<doubleArray.length; i++) {
            doubleArray[i] = buffer.getDouble();
            debug("double value: " + doubleArray[i]);
        }

        return result;
    }

    //Command implementation:
    // Just echo PING - should return the id of the sensor
    public byte ping() {
        byte[] receiveBuffer = new byte[1];
        fetchCommand("P".getBytes(StandardCharsets.US_ASCII), "Ping", receiveBuffer);
        return receiveBuffer[0];
    }
    // echo FAST - change the rate of the blinking LED
    public boolean fast() {
        return basicCommand("F".getBytes(StandardCharsets.US_ASCII), "Fast", "FAST".getBytes());
    }

    // echo SLOW - change the rate of the blinking LED
    public boolean slow() {
        return basicCommand("S".getBytes(StandardCharsets.US_ASCII), "Slow", "SLOW".getBytes());
    }

    // Blink rate - set then fetch the current blink rate
    public boolean blink(int rate) {
        byte[] receiveBuffer = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(5);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.put("B".getBytes(StandardCharsets.US_ASCII)[0]);
        buffer.putInt(rate);
        return fetchCommand(buffer.array(), "Blink", receiveBuffer);
    }

    // Actually the default command - fetches a two byte value tied to a simple counter
    public boolean fetchCounter() {
        byte[] receiveBuffer = new byte[2];
        return fetchCommand("\0".getBytes(), "FetchCounter", receiveBuffer);
    }


//    protected void doIt() {
//
//        byte b;
//        int sleep = 5;
//
//        try {
//            // setup
//            m_clientSocket = new Socket("192.168.1.177", 5422);
//            m_outToServer = new DataOutputStream(m_clientSocket.getOutputStream());
//            m_backFromServer= new DataInputStream(m_clientSocket.getInputStream());
//
//            // some basic tests
//            log("Ping test returned " + Byte.toString(ping()));
//            TimeUnit.SECONDS.sleep(sleep);
//            log("Fast test returned " + (fast() ? "true" : "false") );
//            TimeUnit.SECONDS.sleep(sleep);
//            log("Slow test returned " + (slow() ? "true" : "false") );
//            TimeUnit.SECONDS.sleep(sleep);
//            log("Blink test returned " + (blink(2000) ? "true" : "false") );
//            TimeUnit.SECONDS.sleep(sleep);
//
//            short[] sensorValues = new short[4];  // 4 sensor values
//            for (int i = 0; i < 2000; i++) {
//                fetchShorts("L", "Lidar", sensorValues);
//                log("Lidar test returned [ " +
//                    sensorValues[0] + " ] [ " +
//                    sensorValues[1] + " ] [ " +
//                    sensorValues[2] + " ] [ " +
//                    sensorValues[3] + " ]");
//                TimeUnit.SECONDS.sleep(1);
//            }
//            m_clientSocket.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
