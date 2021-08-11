from utills import *
conn,cur=start("loja")
produtos_p="produto_id integer primary key autoincrement,nome text,valor real,categoria_id integer,constraint fk_categorias foreign key (categoria_id) references categorias(categoria_id)"
criarTabela("categorias","categoria_id integer primary key, categoria text")
criarTabela("produtos",produtos_p)
categorias=[(1,"mercearia"),
            (2,"frutas"),
            (3,"açougue"),
            (4,"higiene"),
            (5,"limpeza")]
produtos=[("feijão",5.10,1),
        ("cuscuz",0.93,1),
        ("maça",7.48,2),
        ("carne de sol",22.98,3),
        ("sabonete xerin",1.10,4),
        ("creme dental dente azul 90g",8.78,4),
        ("sabão em pó xô 'nhaca pôdji 400g",9.89,5)]
insertInto("categorias","categoria_id,categoria",categorias)
insertInto("produtos","nome,valor,categoria_id",produtos)
#cur.executemany("insert into produtos (nome,valor,categoria_id) values (?,?,?)",produtos)
buscaTabela("categorias")
buscaTabela("produtos")
conn.commit()
conn.close()

