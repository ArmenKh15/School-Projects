#!/bin/bash

#Store year as a variable
year=$1

#Create main directory
mkdir "${year}_calendar"

#Change directory to calendar directory
cd "${year}_calendar"

#Create folder for each month
mkdir Month{01..12}

#Create txt files for months with 31 days
touch Month{01,03,05,07,08,10,12}/Day{01..31}.txt

#Create txt files for months with 30 days
touch Month{04,06,09,11}/Day{01..30}.txt

#Create txt files for February
touch Month02/Day{01..28}.txt

#Completion message
echo "calendar for $year is complete."