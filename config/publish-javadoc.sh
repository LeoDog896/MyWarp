#!/bin/bash
#
# Publish Javadoc of successful CI builds to https://thee.github.io/MyWarp/javadoc
# See https://web.archive.org/web/20150107174657/http://benlimmer.com/2013/12/26/automatically-publish-javadoc-to-gh-pages-with-travis-ci/

if [ "$TRAVIS_REPO_SLUG" == "TheE/MyWarp" ] && \
   [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && \
   [ "$TRAVIS_PULL_REQUEST" == "false" ] &&  \
   [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Publishing javadoc...\n"

  mkdir -p $HOME/javadoc-latest/mywarp-core
  cp -R mywarp-core/build/docs/javadoc/ $HOME/javadoc-latest/mywarp-core

  mkdir -p $HOME/javadoc-latest/mywarp-bukkit
  cp -R mywarp-bukkit/build/docs/javadoc/ $HOME/javadoc-latest/mywarp-bukkit

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/TheE/MyWarp gh-pages > /dev/null

  cd gh-pages
  git rm -rf ./javadoc
  cp -Rf $HOME/javadoc-latest ./javadoc
  git add -f .
  git commit -m "Lastest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"

fi