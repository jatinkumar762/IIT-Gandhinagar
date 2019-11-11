import random
import matplotlib.pyplot as plt

class PageReplacement:
    def __init__(self):
        self.frame_seq = []           #Frames which are referenced by program
        self.frame_num = 0            #Total No. of Frames
        self.Page_size = 0            #No of pages in memoy
        self.Page_mem = []            #Currently residing frames in pages of memory
        self.hit = 0                  #No. of hits
        self.miss = 0                 #No. of miss
        self.total_ref = 0            #No of times memory referenced
          
    def getFrames(self):                             #Initialize list of reference frames
        self.frame_num=int(random.random()*100)
        for i in range(self.frame_num):
		        self.frame_seq.append(int(random.random()*100))
        print("Reference String ",self.frame_seq)
    
    def reint_data(self):              #Reinitialize data 
        self.hit = 0
        self.miss = 0
        self.total_ref = 0
        self.Page_mem = []

    #Least Recently Used
    def LRU(self):                                    
        self.reint_data()
        for frame in self.frame_seq:
            if frame in self.Page_mem:                 #frame already present in memory
                index = self.Page_mem.index(frame)    
                del self.Page_mem[index]               #delete from current place
                self.Page_mem.insert(0, frame)         #reagain insert at begining
                self.total_ref += 1
                self.hit += 1
            elif len(self.Page_mem)<self.Page_size:     #space available in memory
                self.Page_mem.insert(0, frame)
                self.total_ref += 1
                self.miss += 1
            elif len(self.Page_mem)>=self.Page_size:    #No Space availabe in memory
                del self.Page_mem[len(self.Page_mem)-1] #delete last page bcz least recently will be always in last 
                self.Page_mem.insert(0, frame)          #insert new frame at the begining
                self.total_ref += 1
                self.miss += 1

    #First-In First-Out
    def FIFO(self):
        self.reint_data()
        for frame in self.frame_seq:                    
            if frame in self.Page_mem:                  #frame aleady present in memory
                self.total_ref += 1
                self.hit += 1
            elif len(self.Page_mem)<self.Page_size:     #space avaiable
                self.Page_mem.insert(0, frame)          #insert at begining
                self.total_ref += 1
                self.miss += 1
            else:
                del self.Page_mem[len(self.Page_mem)-1]  #delete last page
                self.Page_mem.insert(0, frame)           #insert at begining
                self.total_ref += 1
                self.miss += 1

    #Least Frequenty Used
    def LFU(self):                            #Use 2-d Array Concept
        self.reint_data()                     #for each page corresponding frequency also there
        for frame in self.frame_seq:
            flag = 0
            for page in self.Page_mem:         
                if page[0]== frame:                                   #frame already present in memory
                    self.Page_mem[self.Page_mem.index(page)][1]+= 1   #increase it's freq. count
                    flag = 1
                    self.total_ref += 1
                    self.hit += 1
                    break
            if flag == 0:
                if len(self.Page_mem) < self.Page_size:               #space availabel
                    self.Page_mem.insert(0,[frame,1])                 #insert at the begining
                    self.total_ref += 1                               
                    self.miss += 1
                else:
                    minCount = [0,10**5]                              
                    for page in self.Page_mem:                        #find page with min frequency
                        if minCount[1] > page[1]:
                           minCount = page
                    del self.Page_mem[self.Page_mem.index(minCount)]
                    self.Page_mem.insert(0,[frame,1])
                    self.total_ref += 1
                    self.miss += 1    

    #Random Page Replacement
    def Random(self):
        self.reint_data()
        for frame in self.frame_seq:            #pick one by one frame from reference array
            if frame in self.Page_mem:          #if page already present in memory
                self.total_ref += 1
                self.hit += 1
            elif len(self.Page_mem)<self.Page_size:  #if space available in memory
                self.Page_mem.insert(0, frame)
                self.total_ref += 1
                self.miss += 1
            else:                                    #if memory already full
                del self.Page_mem[(int(random.random()*100))%self.Page_size] #select any random page location in memory
                self.Page_mem.insert(0, frame)
                self.total_ref += 1
                self.miss += 1

    #Oracle - Predict Next Replacement Page
    def Oracle(self):
        self.reint_data()
        for i,frame in enumerate(self.frame_seq):   #frame with it's position
            if frame in self.Page_mem:              #check if page already prsent in memory
                self.total_ref += 1
                self.hit += 1
            elif len(self.Page_mem)<self.Page_size: #if space available in memory
                self.Page_mem.insert(0, frame)
                self.total_ref += 1
                self.miss += 1
            else:
                far = 0                             #if memory already full
                del_page = 0
                for page in self.Page_mem:          #choose farthest page in memory according to next access position in frame reference
                    try:                            #ValueError Possible
                        if far < (self.frame_seq[i:]).index(page):
                            far = (self.frame_seq[i:]).index(page)
                            del_page = page
                    except:
                        del_page = page
                        break
                del self.Page_mem[self.Page_mem.index(del_page)]   #delete selected location page from memory
                self.Page_mem.insert(0, frame)    #insert new page in memory
                self.total_ref += 1
                self.miss += 1
    
    #Approx Least Recenty Used Algorithm
    #Theory concept - https://www.geeksforgeeks.org/lru-approximation-second-chance-algorithm/
    def ALRU(self):
        self.reint_data()
        loop = 0                      #circular list counter initially starts from 0
        flag = 0
        del_page = 0                  #page to be deleted fro memory
        for frame in self.frame_seq:
            flag = 0
            for page in self.Page_mem:            #page already present in memory
                if page[0] == frame:       
                    page[1] = 1                   #set use/reference bit 1
                    self.hit += 1
                    self.total_ref += 1
                    flag = 1
                    break
            if flag == 0:
                if len(self.Page_mem)<self.Page_size:
                    self.Page_mem.insert(0, [frame,1])   #Newly inserted fresh page
                    self.total_ref += 1
                    self.miss += 1
                else:
                    for index, page in enumerate(self.Page_mem,start=loop):   #start from previous selected point
                        if page[1] == 0:
                            flag=1
                            del_page=page
                            if index+1 < self.Page_size:
                                loop= index+1
                            else:
                                loop = 0
                            break
                        else:
                            page[1]=0
                    
                    if flag==0:                                            #still not found page then again start from begining
                        for index, page in enumerate(self.Page_mem):
                            if page[1] == 0:
                                flag=1
                                del_page=page
                                if index+1 < self.Page_size:
                                    loop= index+1
                                else:
                                    loop=0
                                break
                            else:
                                page[1]=0

                    del self.Page_mem[self.Page_mem.index(del_page)]
                    self.Page_mem.insert(0, [frame,1])
                    self.total_ref += 1
                    self.miss += 1


if __name__=="__main__":
    memory = PageReplacement()
    page_list = list(range(1,101))
    memory.getFrames()
    lru_hit = []
    fifo_hit = []
    lfu_hit = []
    rndom_hit = []
    oracle_hit = []
    alru_hit = []
    rate = 0;
    for i in range(1,101):
        memory.Page_size = i
        memory.LRU()
        rate=((memory.hit*100)/memory.total_ref)
        lru_hit.append(rate)
        memory.FIFO()
        rate=((memory.hit*100)/memory.total_ref)
        fifo_hit.append(rate)       
        memory.LFU()
        rate=((memory.hit*100)/memory.total_ref)
        lfu_hit.append(rate)
        memory.Random()
        rate=((memory.hit*100)/memory.total_ref)
        rndom_hit.append(rate)
        memory.Oracle()
        rate=((memory.hit*100)/memory.total_ref)
        oracle_hit.append(rate)
        memory.ALRU()
        rate = ((memory.hit*100)/memory.total_ref)
        alru_hit.append(rate)


    plt.xlabel('Page Size (Blocks)')
    plt.ylabel('Page Hit Rate (%)')
    plt.plot(page_list,lru_hit,c='r',label='LRU')
    plt.plot(page_list,fifo_hit,c='g',label='FIFO')
    plt.plot(page_list,lfu_hit,c='k',label='LFU')
    plt.plot(page_list,rndom_hit,c='b',label='Random')
    plt.plot(page_list,oracle_hit,c='y',label='Oracle')
    plt.plot(page_list,alru_hit,c='m',label='ALRU')
    plt.legend()
    plt.show()