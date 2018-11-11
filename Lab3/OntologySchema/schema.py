import csv
words = {}
file = open('MedicalWords.csv','r')
reader = csv.reader(file)
for row in reader:
    key = row[0].replace(" ","").replace("-","").replace("/","")
    value = row[1]
    words[key] =value

print "done!"

individuals={}
for line in open('TripletList','r'):
    terms = line.split(",")
    sub = terms[0]
    pre = terms[1]
    obj = terms[2]
    for key in words:
        if sub.__contains__(key):
            individuals[sub] = words[key]
            #print sub +" "+words[key]
        if obj.__contains__(key):
            individuals[sub] = words[key]

txtName = "Individuals"
f = open(txtName,"a")
for key in individuals:
    print key
    print individuals[key]
    temp = key+","+individuals[key]+"\n"
    f.write(temp)
f.close

objectProperties=[]
for line in open('TripletList','r'):
    terms = line.split(",")
    sub = terms[0]
    pre = terms[1]
    obj = terms[2]
    subProperties = "Subject"
    objProperties = "Object"
    for key in individuals:
        if sub==key:
            subProperties = individuals[key]
            #print sub +" "+words[key]
        if obj==key:
            objProperties = individuals[key]
    temp = pre+","+subProperties+","+objProperties+",Func\n"
    objectProperties.append(temp)

txtName = "ObjectProperties"
f = open(txtName,"a")
for o in objectProperties:
    print o
    f.write(o)
f.close