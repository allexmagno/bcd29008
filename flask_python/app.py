from flask import Flask, render_template, request, url_for, redirect

from sqlalchemy import create_engine
from sqlalchemy.ext.automap import automap_base
from sqlalchemy.orm import sessionmaker

app = Flask(__name__)
app.secret_key = 'PADSOJ29sdaj'



engine = create_engine("sqlite:///lab05.sqlite")
Session = sessionmaker(bind=engine)
Base = automap_base()
Base.prepare(engine, reflect=True)

Pessoa = Base.classes.Pessoa
Telefones = Base.classes.Telefones


@app.route('/')
def hello_world():
    #return 'Hello World!'
    return render_template('index.html')

@app.route('/listar', methods=['GET'])
def listar():

    session = Session()
    pessoas = session.query(Pessoa).all()
    session.close()

    return render_template('listar.html', lista_pessoas=pessoas)

@app.route('/cadastrar', methods=['GET', 'POST'])
def cadastrar_pessoa():
    if request.method == 'GET':
        return render_template('cadastrar.html')

    else:

        nome = request.form['nome']
        tel = request.form['telefone']
        session = Session()

        pessoa = Pessoa()
        pessoa.nome =nome

        telefone = Telefones()
        telefone.numero = tel

        pessoa.telefones_collection.append(telefone)

        session.add(pessoa)
        session.commit()
        session.close()

        return redirect(url_for('listar'))

@app.route('/editar', methods=['GET', 'POST'])
def editar_pessoa():

    if request.method == 'GET':

        idP = str(request.args.get('id'))
        session = Session()
        pessoa = session.query(Pessoa).filter(Pessoa.idPessoa==idP).first()

        telefones = session.query(Telefones).filter(Telefones.idPessoa == idP).all()
        session.close()

        return render_template('editar.html', pessoa = pessoa, telefones=telefones)

    else:
        idP = request.form['idP']
        session = Session()
        pessoa = session.query(Pessoa).filter(Pessoa.idPessoa == idP).first()

        nome = request.form['nome']
        pessoa.nome = nome

        for campo in request.form.items():
            if 'tel-' in campo[0]:
                idTel = campo[0].split('-')[1]
                for tel in pessoa.telefones_collection:
                    if tel.idTelefone == int(idTel):
                        tel.numero = campo[1]
        novo = request.form['novoTel']

        if novo is not None:
            telefone = Telefones()
            telefone.numero = novo
            pessoa.telefones_collection.append(telefone)

        session.commit()
        session.close()

        return redirect(url_for('listar'))

#Capturar o que vem da URL
@app.route('/excluir', methods=['GET', 'POST'])
def excluir():

    if request.method == 'GET':

        idP = str(request.args.get('id'))
        session = Session()
        pessoa = session.query(Pessoa).filter(Pessoa.idPessoa==idP).first()
        session.close()
        return render_template('excluir.html', pessoa=pessoa)

    else:

        idP = request.form['idP']
        session = Session()
        pessoa = session.query(Pessoa).filter(Pessoa.idPessoa == idP).first()

        # excluindo todos os telefones associados com pessoa
        pessoa.telefones_collection[:]
        session.delete(pessoa)
        session.commit()
        session.close()

        return redirect(url_for('listar'))



if __name__ == '__main__':
    app.run(debug=True)
