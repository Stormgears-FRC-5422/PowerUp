#!/bin/bash
set -e

SOURCE_BRANCH="master"
TARGET_BRANCH="gh-pages"

SSH_REPO='git@github.com:Stormgears-FRC-5422/PowerUp.git'
SHA=`git rev-parse --verify HEAD`

eval $(ssh-agent -s)
echo "$SSH_PRIVATE_KEY" | tr -d '\r' | ssh-add - > /dev/null
mkdir -p ~/.ssh
chmod 700 ~/.ssh
[[ -f /.dockerenv ]] && echo -e "Host *\n\tStrictHostKeyChecking no\n\n" > ~/.ssh/config

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
