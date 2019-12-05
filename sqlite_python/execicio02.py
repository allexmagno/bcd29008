from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.automap import automap_base


def to_string(pessoa):
    for p in pessoa:
        print("Id: {}\nNome: {}".format(p.idPessoa, p.nome))

def to_string_tel(pessoa):
    for p in pessoa:
        print("Id: {}\nNome: {}".format(p.idPessoa, p.nome))
        for t in pessoa.telefones_collection:
            print("tel: {}".format(t.numero))


if __name__ == '__main__':
    engine = create_engine('sqlite:///lab05-ex02.sqlite')
    Session = sessionmaker(bind=engine)
    session = Session()

    Base = automap_base()
    Base.prepare(engine, reflect=True)

    Pessoa = Base.classes.Pessoa
    Telefones = Base.classes.Telefones

    pessoas = session.query(Pessoa).all()
    to_string(pessoas)


    tel = session.query(Telefones).all()
    print(tel[0].numero)
    #letra = input("Inicial: ")
    #pessoas = session.query(Pessoa).filter(Pessoa.nome.ilike(letra+'%')).all()
    #to_string(pessoas)

    pessoas = session.query(Pessoa).join(Telefones).all()
    print(pessoas.telefones_collection)
    to_string_tel(pessoas)
