#!/usr/bin/env bash
export GPG_TTY=$(tty)
/Users/ddshuai/soft-install/soft/maven/apache-maven-3.6.3/bin/mvn clean deploy -P release