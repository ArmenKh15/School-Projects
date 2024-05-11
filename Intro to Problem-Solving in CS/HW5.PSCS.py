"""
@date: <9/22/23>
@author: <Armen Khachatryan>
@assignment: <HW5>
@note: Do NOT alter the function headers that are well documented
"""


def max_difference(values: list) -> float:
    """ Efficiently finds the largest difference between any two elements in a list
    @param values: a list of numbers
    @return number for the largest difference between elements
    """
    min_num = values[0] #Declare min num variable
    max_num = values[0] #Declare max num variable
    max_diff = 0 #Declare max difference variable
    for k in values: #Begin for loop ( O(n) )
        if k < min_num: # Compare current min num to current k, update if necessary
            min_num = k
        if k > max_num: # Compare current max num to current k, update if necessary
            max_num = k
        if max_diff < (max_num-min_num): #Compare current max diff to current max_num-min_num, update if necessary
            max_diff = max_num-min_num
    return max_diff

def sort_bivalued(values: list) -> list:
    """Efficiently sort a list of binary values
    @param values: a list of binary digits (0 or 1)
    @return: a list of binary numbers in ascending sort order
    """
    index = 0
    for i in range(len(values)): #Begin for loop ( O(n) )
        if values[i] == 0: #If value at i is equal to 0, swap with value at index which puts zeros at front of list
            values[i], values[index] = values[index], values[i]
            index += 1
    return values