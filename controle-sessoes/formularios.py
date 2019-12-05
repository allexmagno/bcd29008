from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, SubmitField
from wtforms.validators import DataRequired


class AutenticacaoForm(FlaskForm):
    username = StringField('Nome do usuário', validators=[DataRequired('Campo obrigatótorio!')])
    password = PasswordField('senha', validators=[DataRequired('Campo obrigatótorio!')])
    login = SubmitField('entrar')