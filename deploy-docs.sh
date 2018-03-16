#!/bin/bash
set -e

SOURCE_BRANCH="master"
TARGET_BRANCH="gh-pages"

# Pull requests and commits to other branches shouldn't try to deploy, just build to verify
if [ "$CI_COMMIT_REF_NAME" != "$SOURCE_BRANCH" ]; then
    echo "Skipping deploy; just doing a build."
    doCompile
    exit 0
fi

SSH_REPO='git@github.com:Stormgears-FRC-5422/PowerUp.git'
SHA=`git rev-parse --verify HEAD`

echo "${DEPLOY_KEY}" > ../deploy_key.base64
base64 -d ../deploy_key.base64 > ../deploy_key
chmod 600 ../deploy_key
eval `ssh-agent -s`
ssh-add ../deploy_key

git clone $SSH_REPO docs-deploy
cd docs-deploy
git checkout $TARGET_BRANCH || git checkout --orphan $TARGET_BRANCH
cd ..

rm -rf docs-deploy/**/* || exit 0

cp -r ./build/dokka/-power-up/* ./docs-deploy/

cd docs-deploy
git config user.name "GitLab CI"
git config user.email "$GITLAB_USER_EMAIL"

if git diff --quiet; then
    echo "No changes to the output on this push; exiting."
    exit 0
fi

git add -A .
git commit -m "Deploy to GitHub Pages: ${SHA}"

git push $SSH_REPO $TARGET_BRANCH
