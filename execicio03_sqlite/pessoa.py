from base import Base
from sqlalchemy import Column, String, INTEGER

class Pessoa(Base):
    __tablename__ = 'Pessoa'
    idPessoa = Column(INTEGER, primary_key=True, autoincrement=True)
    nome = Column(String)


    def __init__(self, nome):
        self.nome = nome
