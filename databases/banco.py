from utills import *

conn,cur=start('banco')
criarTabela("tracks","title text,plays integer")
music=[('trunder',20),
        ('my way',15)]
insertInto("tracks","title,plays",music)
#cur.executemany("insert into tracks (title,plays) values (?,?)",music)
buscaTabela("tracks","title")
conn.commit()
conn.close()
