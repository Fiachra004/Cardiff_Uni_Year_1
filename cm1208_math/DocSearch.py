from collections import defaultdict
import numpy as np
import math as math


# Opens and closes the file creating a dictionary called "Documents" with every line given an ID
Documents = {}
with open("docs.txt", "r") as file:
    for id, line in enumerate(file, start=1):
        line = line.replace("\n", "")
        line = line.replace("\t", " ")
        line = line.strip() 
        Documents[id] = line
file.close()

# Dictionaraies called "Dictioanry" and "index" are created, "Dictionary" will hold the word and its ID whic will then 
# link to the index which will tell us what line it appears in the documents, and how many times it appears using this happens in subdict
# deafultdict is used to deal with not yet set keys and also creating subdictionaries within these keys. word_set is used so we don't add duplicate to dictionary
Dictionary = {}
index = defaultdict(dict)
word_set = set()

# for loop that will add words to dicitonary if it is not in the set.
# Also will add document ID to subdictionary as key and will add to that value if the key is already present in subdictionary
for doc_id, text in Documents.items():
    templist = text.split(" ")
    for word in templist:
        if word not in word_set:
            Dictionary[word] = len(Dictionary) + 1
            word_set.add(word)
        if doc_id not in index[Dictionary[word]]:
            index[Dictionary[word]][doc_id] = 0
        index[Dictionary[word]][doc_id] += 1

#This opens the queries txt file creating every line into a striped list which is then added to the queris list 

with open("queries.txt", "r") as query_file:
    query_list = []
    for line in query_file:
        line.replace("\n", "")
        line = line.strip()
        line = line.split()
        query_list.append(line)
query_file.close()

# function to find the angle between two array by using the dot product on them then dividing them by the product of the two normalized arrays

def calc_angle(x, y): 
	norm1 = np.linalg.norm(x) 
	norm2 = np.linalg.norm(y) 
	cos_theta = np.dot(x, y) / (norm1 * norm2) 
	angle = math.degrees(math.acos(cos_theta)) 
	return angle

# As we reach the final for loop we can begin to print structure of the outputs

print("Words in dictionary : " + str(len(Dictionary)))

# first for loop loops through the queries in the 2d list query list meaning it loops only through lists again
# Array full of 0s "query_array" created which is the length of dictionary the queried words will then be turned to 1 in there position in the dictionary
# "first_word" gives the first query in the nested list it then adds all of the keys from the indexes subdictionary which are the documents ids to "doc_list"

for query in query_list:
    query_array = np.zeros(len(Dictionary))
    doc_list = []
    first_word = query[0]
    if first_word in Dictionary:
        query_array[Dictionary[first_word] - 1] = 1
        if Dictionary[first_word] in index:
            doc_list.extend(index[Dictionary[first_word]].keys())

# first nested for loop will check if there are more words and alter the "query_array" further so it matches the query in array format
# it will then create a turn doc_list into a set and will make the keys from the subdictionary of that value into a set, and the intersection will be the new doc_list
# this means the new doc_list will only contain document Ids which are shared by all of the queries
            
    for word in query[1:]:
        if word in Dictionary:
            query_array[Dictionary[word] - 1] = 1
            if Dictionary[word] in index:
                doc_list = list(set(doc_list) & set(index[Dictionary[word]].keys()))

# angle dict is initialized to create dictionary we can add document ids and there corresponding angles to to show how much they match with the query 
# second nested for loop spliting document (line) into a list and initialzing array for that document
    angle_dict = defaultdict()
    for docs in doc_list:
        word_list = Documents[docs].split(" ")
        doc_array = np.zeros(len(Dictionary))

# third nested loop to alter the array so that it represents the amount of times it appears in the document in an array the length of a dictionary 
# we can then finally use these arrays to calculate the angle and present the values

        for word in word_list:
            doc_array[Dictionary[word]-1] += 1
        angle_dict[docs] = calc_angle(doc_array, query_array)
        sorted_angle = sorted(angle_dict.items(), key=lambda x:x[1])
        sorted_angle_dict = dict(sorted_angle)
    print("Query:  " + " ".join(query))
    print("Relevant documents :", " ".join(str(doc_id) for doc_id in doc_list))
    for data in sorted_angle_dict:
        print(data, round(sorted_angle_dict[data], 5))
        
