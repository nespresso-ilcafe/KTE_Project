import json
import pandas as pd

d4_str = """
[
{"name":"혼자 공부하는 데이터 분석","author":"박해선","year":2022},
{"name":"혼자 공부하는 머신러닝+딥러닝","author":"박해선","year":2020}
]
"""
# 1번
# list = json.loads(d4_str)
# print(list[0]["name"])

# 2번
# pd_list = pd.DataFrame(list)
# print(pd_list)


x_str = """
<book>
    <name>혼자 공부하는 데이터 분석</name>
    <author>박해선</author>
    <year>2022</year>
</book>
"""
# 3번
# import xml.etree.ElementTree as et
# book = et.fromstring(x_str)
#
# name = book.findtext("name")
# author = book.findtext("author")
# year = book.findtext("year")
#
# print(name)
# print(author)
# print(year)

import gdown
# gdown.download('https://bit.ly/3q9SZix','20s_best_book.json',quiet=False)
#
# # 4번
# df = pd.read_json('20s_best_book.json')
# print(df.head())


import requests
from bs4 import BeautifulSoup

# 5번
# isbn = 9791190090018
# url = 'https://www.yes24.com/Product/Search?domain=BOOK&query={}'
# r = requests.get(url.format(isbn))
# soup = BeautifulSoup(r.text,'html.parser')
#
# fir = soup.find('a', attrs={'href':'/Product/Goods/74261416'})
# sec = fir.get('href')
#
# print(sec)

# 6번
# df1 = pd.DataFrame({
#     'col1':['a','b','c'],
#     'col2':['1','2','3']
# })
# df2 = pd.DataFrame({
#     'col1':['a','b','d'],
#     'col3':['10','20','30']
# })
#
# res = pd.merge(df1,df2, on='col1', how='left')
# print(res)


# gdown.download('https://bit.ly/3RhoNho','ns_202104.csv',quiet=False)
#
# # 7번
# ns_df = pd.read_csv('ns_202104.csv',low_memory=False)
# sel = ['번호','도서명','저자','출판사','발행년도','ISBN','세트 ISBN','부가기호','권','주제분류번호','도서권수','대출건수','등록일자']
# # print(ns_df[sel])
#
# # 8번
# col = [0,1]
# ns_df = ns_df.drop(index=col)
# print(ns_df[sel])


# gdown.download('https://bit.ly/3736JW1','ns_book6.csv',False)
#
# ns_df = pd.read_csv('ns_book6.csv')
# statistics_df = ns_df.describe()
#
# print(statistics_df)

import matplotlib.pyplot as plt

gdown.download('https://bit.ly/3pK7iuu','ns_book7.csv',False)
ns_bo7 = pd.read_csv('ns_book7.csv', low_memory=False)
ns_bo7= ns_bo7.describe()

da = ns_bo7['대출건수']

gra = da.plot(kind='bar',figsize=(5,5))
plt.yscale('log',base=10)

plt.show()









