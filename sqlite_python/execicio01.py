import sqlite3


def to_string(pessoa):
    for linha in pessoa:
        print('id: {}\nNome: {}'.format(linha[0], linha[1]))

if __name__ == '__main__':

    con = sqlite3.connect('lab05-ex01.sqlite')

    cursor = con.cursor()

    cursor.execute("SELECT * FROM Pessoa")
    print(cursor.fetchall())

    nome = input("Nome da pessoa: ")
    clausula = (nome,)

    cursor.execute("SELECT * FROM Pessoa WHERE nome = ?", clausula)
    pessoa = cursor.fetchall()
    to_string(pessoa)


    cursor.close()
    con.close()