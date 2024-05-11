"""
@date: <9/29/23>
@author: <Armen Khachatryan>
@assignment: <HW6>
@note: Do NOT alter the function headers that are well documented
"""

def sum_digits(number: int) -> int:
    """ Sums each digit of a number together using recursion
    @param number: an integer whose digits will be summed
    @return: the sum of all digits in the number
    """
    if number < 0: #If number is negative
        return sum_digits(abs(number))
    if number < 10: #If single digit return
        return number
    
    last = number % 10 #Store last digit
    rest = number // 10 #Remove last digit
    return last + sum_digits(rest) #Recursive adding last digit then removing it until number is a single digit


def is_diff_two(values: list, diff: int) -> bool:
    """ Checks if there are two elements within a list that have a specific difference between them using recursion 
    @param values: The list of integer values to be searched
    @param diff: The difference value between two elements to find
    @return: True if there are two elements in values with a difference of diff, otherwise False
    """
    def helper(index1=0,index2=1): #Helper function
        if index1 >= len(values): #When reaching end of list and no answer, False
            return False
        if index2 == len(values): #If second index reaches end of list
            return helper(index1+1, index1+1)
        if abs(values[index1]-values[index2])==diff: #Check if absolute value of difference is requested diff, True
            return True
        return helper(index1,index2+1) #Move to next index
    return helper()
