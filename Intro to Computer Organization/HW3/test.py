#!/bin/python3
import os
import sys

if __name__ == "__main__":
    # Check if the number of arguments to the program is less than 2; the second argument is the path to the calendar directory
    if len(sys.argv) < 2:
        print(
            "Calendar directory path missing.\nUsage: check_calendar_files.py <calendar_path>"
        )
        sys.exit(1)

    # Check if the calendar directory exists
    calendar_path = sys.argv[1]
    if not os.path.exists(calendar_path):
        print(f"Calendar path {calendar_path} does not exist.")
        sys.exit(1)

    # iterate through the months in the calendar directory
    for month in range(1, 13):
        month_path = os.path.join(
            calendar_path, f"Month{month:02d}"
        )  # month:02d formats the month number to 2 digits

        # Check if the month directory exists
        if not os.path.exists(month_path):
            print(f"Month {month} is missing.")
            sys.exit(1)

        # set expected days in the month
        if month == 2:
            expected_days = 28
        elif month in [4, 6, 9, 11]:
            expected_days = 30
        else:
            expected_days = 31

        # Check if the number of files in the month directory is equal to the expected days
        day_files = [
            f
            for f in os.listdir(month_path)
            if os.path.isfile(os.path.join(month_path, f))
        ]
        if len(day_files) != expected_days:
            print(
                f"Expected {expected_days} files in {month_path}, found {len(day_files)}."
            )
            sys.exit(1)

        # Iterate through the days in the month directory and check if the day file exists
        for day in range(1, expected_days + 1, 1):
            day_path = os.path.join(month_path, f"Day{day:02d}.txt")
            if not os.path.exists(day_path):
                print(f"Day {day} is missing in month {month}.")
                sys.exit(1)
