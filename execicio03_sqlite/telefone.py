from sqlalchemy.orm import relationship

from base import Base
from sqlalchemy import Column, String, INTEGER, ForeignKey


class Telefone(Base):
    __tablename__ = 'Telefone'

    idTelefone = Column(INTEGER, primary_key=True, autoincrement=True)
    numero = Column(String)
    idPessoa = Column(INTEGER, ForeignKey("Pessoa.idPessoa"))

    Pessoa = relationship("Pessoa", backref="Telefone")

    def __init__(self, numero, pessoa):
        self.numero = numero
        self.Pessoa = pessoa