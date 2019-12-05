from flask import Flask, render_template
from flask_bootstrap import Bootstrap
from flask_nav import Nav
from flask_nav.elements import Navbar

app = Flask(__name__)
app.secret_key = "asdasdasds"

bootstrap = Bootstrap(app)
nav = Nav()

@nav.navigation()
def menunav():
    menu = Navbar('Meu site')
    menu.items = [View('Home, inicial'), View('Cadastro', 'inicial')]

@app.route('/')
def inicial():
    return  render_template('index.html')


if __name__ == '__main__':
    app.run()
