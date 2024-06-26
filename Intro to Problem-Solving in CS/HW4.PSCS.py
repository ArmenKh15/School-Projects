"""
@date: <9/15/23>
@author: <Armen Khachatryan>
@assignment: <HW4>
@note: Do NOT alter the function headers that are well documented, 
Do put your hw answers in the spaces provided within function headers
"""


def log_base_2(number: float) -> str:
    """ Gives the approximate log base 2 calculation of the input number
    @param number: float to calculate on
    @precondition: number is > 0
    @return str: A string description of the approximate result
    parts i-iii from HW
    i. Work out the steps to figure out the 2 concrete examples of 256 and 81
     step by step and briefly explain your work and thinking(5pts)

    For 256: First the code declares the count and count2 variables. Then it checks if number is
    less than or equal to zero, which would result in an undefined answer. Since it is not, it
    goes to the while which goes on until number is greater than 1. First it divides number by 2,
    and adds to count, then checks to see if number is still a whole number. Its still a whole
    number so the loop restarts with number = 128. It repeats this process 7 more times until number
    is equal to 1 and count is equal to 8. Since count2 was never used, the function returns count
    which is 8 and the answer of logbase2(256).

    For 81: First the code declares the count and count2 variables. Then it checks if number is
    less than or equal to zero, which would result in an undefined answer. Since it is not, it
    goes to the while which goes on until number is greater than 1. First it divides number by 2,
    and adds to count, then checks to see if number is still a whole number. Since it is now 40.5, we
    add to count2 and turn number into a whole number using floor division so the code doesnt have to run the if statement again. 
    We repeat this process 6 more times until number is less than or equal to 1. Since 
    the answer would not be a whole number, we need to return the answer within bounds to which a final 
    if statement checks if count2 is greater than 0, if so then it confirms we need to return our answer
    in bounds. The count variable is our lower bound, and count+1 is our upper bound. The function returns
    the lower and upper bounds of our answer which would be 6 and 7.

	ii. Find and describe a pattern and attempt to generalize (5pts)

    Since an answer that isnt a power of 2 will result in an odd number at some point, dividing it by 2
    would result in a fraction which is why I check if number is an integer as if it is not, then my final
    output will require bounds. Also, by turning this fraction into a whole number, I do not need to waste 
    future runtime with this if statement as it will never run it again. 

	iii. Investigate and explain special cases to see if the pattern holds up (5pts)

    logbase2(1) is equal to 0, to which the code holds up and returns 0.
    """
    count = 0 #Declare both count variables
    count2 = 0
    if number <= 0: #If number results in undefined answer
        return "undefined"
    if 0<number<1: #If number is a fraction
        number *=100 #Multiply by 100 to make whole numbers
        while number < 100: #Beggining of while loop
            number*=2
            count+=1
            if number > 100: #If answer does not result in a whole number
                count2+=1
        return f"between -{str(count)} and -{str(count-1)}" if count2 > 0 else f'-{str(count)}' #returns  bounds if it isnt whole number, otherwise the answer
    while number > 1: #Beginning of while loop for logbase2
        number /= 2
        count +=1
        if number.is_integer() != True: #If logbase2(number) does not result in a whole number
            number //= 1
            count2 += 1
    return f"between {str(count)} and {str(count+1)}" if count2 > 0 else str(count) #returns  bounds if it isnt whole number, otherwise the answer

print(log_base_2(0.01))


def reverse_list(aList: list) -> list:
    """Gives a list with the elements in reversed order
    @param aList: list input that's going to be reversed
    @return list: the reversed version of the input list
    parts i-iii from HW
    i. Work out the steps to figure out a concrete example and briefly explain
     your work and thinking(5pts)

    First, find size of list and store in respective variable. Then begin the for loop for function.
    Loops for half the size of the list, with each iteration switching the current value with that of
    the opposite end of list. Then finally returns list 

	ii. Find and describe a pattern and attempt to generalize (5pts)

    Since iterating through the entire list would not properly reverse the list, I
    only go through it for half works significantly better and runs faster. Also, if list
    has an odd size, it runs for the list//2 leaving the middle number untouched. This method 
    allows for the function to run for any length list.

	iii. Investigate and explain special cases to see if the pattern holds up (5pts)

    Only special case would be that of a list of length 1 and 0, which both work perfectly fine
    with given code.

    """
    size = len(aList) #Size of list
    for i in range(size//2): #Beginning of for loop, runs for half the size of list
        a = aList[i] #Store current value in variable
        aList[i] = aList[size-(i+1)] #Replace current variable with value at opposite end of list
        aList[size-(i+1)] = a #Put the current variable at opposite end of list
    return aList #Return list

print(reverse_list([1,2,3,4,5]))