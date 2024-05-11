'''
https://machinelearningmastery.com/machine-learning-in-python-step-by-step/
Loads a dataset with assosciated attribute names, then reports on details
of the dataset including statistics and graphs
'''

# Load libraries
import pandas
from pandas.plotting import scatter_matrix
import matplotlib.pyplot as plt


# Load dataset
file = "iris.csv"
names = ['sepal-length', 'sepal-width', 'petal-length', 'petal-width', 'class']

# load data from the csv file with columns labeled by names into a DataFrame called dataset
dataset = pandas.read_csv(file, names=names)

# shape: Return a tuple representing the dimensionality of the DataFrame.
print("The dimensions of the dataset "+file)
print(dataset.shape)

# head: This function returns the first n (20 in this case) rows for the object based on position. 
# It is useful for quickly testing if your object has the right type of data in it.
print("Below are the first 20 rows of the dataset")
print(dataset.head(20))

# descriptions: Generate descriptive statistics including those that summarize the central tendency, 
# dispersion and shape of a dataset’s distribution, excluding NaN values.
print("Descriptions of the statistics of the dataset")
print(dataset.describe())

# class distribution: Group DataFrame using a mapper or by a Series of columns involving some 
# combination of splitting the object, applying a function, and combining the results. 
# This can be used to group large amounts of data and compute operations on these groups.
# In this case, grouping by the size of each class.
print("The size of each class found in the dataset")
print(dataset.groupby('class').size())

# box and whisker plots
dataset.plot(kind='box', subplots=True, layout=(
    2, 2), sharex=False, sharey=False)
plt.savefig('box.png')

# histograms
dataset.hist()
plt.savefig('hist.png')

# scatter plot matrix
scatter_matrix(dataset)
plt.savefig('matrix.png')

print("The corresponding graphs/charts will be created in the directory that this python file is found in")
