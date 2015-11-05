#!/bin/sh

rm db-competence-installer-0.1.deb
dpkg -b db-competence-installer-0.1
sudo dpkg -i db-competence-installer-0.1.deb
