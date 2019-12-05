from base import Session, engine, Base
from pessoa import Pessoa
from telefone import Telefone


def popular_banco():
    session = Session()
    magno = Pessoa('Magno')
    ze = Pessoa('Ze')

    session.add(magno)
    session.add(ze)
    session.commit()

    ze_casa = Telefone("(48) 2133-1323", ze)
    ze_cel = Telefone("(48) 99821-2123", ze)
    session.add(ze_casa)
    session.add(ze_cel)

    session.commit()


if __name__ == '__main__':
    #Base.metadata.create_all(engine)
    popular_banco()