import csv
# words = {}
# file = open('MedicalWords.csv','r')
# reader = csv.reader(file)
# for row in reader:
#     key = row[0].replace(" ","").replace("-","").replace("/","")
#     value = row[1]
#     words[key] =value
#
# print "done!"
result = {}
i=0
for line in open('TripletList','r'):
    i = i+1
    terms = line.split("#")
    triplets = terms[0].split("|")

    for triplet in triplets:
        if triplet=="":
            print "ue"
            continue
        temp = triplet.split(",")

        sub = temp[0]
        pre = temp[1]
        obj = temp[2]
        # temp = [sub,pre,obj]
        # temp2 = "Abstract"+str(index)
        if i not in result:
            result[i]=[]
            result[i].append(sub)
            result[i].append(pre)
            result[i].append(obj)
        else:
            if sub not in result[i]:
                result[i].append(sub)
            if pre not in result[i]:
                result[i].append(pre)
            if obj not in result[i]:
                result[i].append(obj)

print result
txtName = "result.csv"
f = open(txtName,"w" )
writer = csv.writer(f)

for key in result:
    t = "Abstract"+str(key)
    writer.writerow([t, result[key]])
f.close