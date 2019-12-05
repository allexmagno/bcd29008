
import os
from flask import Flask, render_template, url_for, redirect, session, send_from_directory
from formularios import AutenticacaoForm

app = Flask(__name__)
app.secret_key = "oaisdoiajs"

@app.route('/favicon.ico')
def favicon():
    return send_from_directory(os.path.join(app.root_path, 'image'),
                               'favicon.ico', mimetype='image/vnd.microsoft.icon')

@app.route('/')
def hello_world():
    return render_template("index.html")

@app.route('/login', methods=['GET','POST'])
def login():
    form = AutenticacaoForm()

    if form.validate_on_submit():
        nome = form.username.data
        senha = form.password.data

        # TODO Verificar se o usuario e senha são validos
        if nome == 'aluno' and senha == 'aluno':
            session['login'] = nome
            return redirect(url_for('dashboard'))
        else:
            return redirect(url_for('login'))
    else:
        return render_template("login.html", formulario=form)
        #return redirect(url_for('login'))


@app.route('/dashboard')
def dashboard():
    usuario = session.get('login')
    if usuario is None:
        return redirect(url_for('login'))
    return render_template('dashboard.html', usuario=usuario)


if __name__ == '__main__':
    app.run(debug=True)
