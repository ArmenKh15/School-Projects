"""
@date: 10/20/23
@author: Armen Khachatryan
@assignment: W9 CW Reading data off html
"""

import re
# use regex to parse the html string and extract the target data

in_filename = 'W9_Web_Development/student_list.html'
out_filename = 'W9_Web_Development/student_list.txt'
# defines the name of the file that you're going to read and write into

content = open(in_filename,"r")
output = open(out_filename,"a")
# content and output are file stream object, you could use 'r'(read), 'w'(write) or 'a'(append)
 
str = content.read() 
# read() method will turn a file stream into a regular string

# use matches = re.findall(pattern,str), you need to figure out what pattern to use
# pattern = r'<td>(\w*)<\/td>\s*<td>(\w*)<\/td>\s*<td>(\d)'
# once you get a match, you access different components from the match object using indices.
# use output.write() to write content to the output file
# for output formatting, you can try rjust()

matches = re.findall(r'<td>(\w*)<\/td>\s*<td>(\w*)<\/td>\s*<td>(\d)',str)
for match in matches:
    if match[2] == '1':
        output.write(f"{(match[1] +', '+ match[0]):>20}{match[2]:>5}\n")
#grade = re.search('1',str)

output.close()
content.close()
#close the stream 