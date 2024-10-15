from flask import Flask, render_template, request, session, redirect, url_for
from flask_bootstrap import Bootstrap

app = Flask(__name__)
bootstrap = Bootstrap(app)
app.config['SECRET_KEY'] = 'your_secret_key'

@app.route('/', methods=['GET', 'POST'])
def index():
    if 'basket' not in session:
        session['basket'] = {}
    
    basket = session['basket']

    if request.method == 'POST':
        item = request.form['item']
        id = request.form['id']
        basket[id] = item
        session['basket'] = basket
        session.modified = True  # Flag session as modified to ensure changes are saved
        print(session['basket'])

    return render_template('index.html', basket=session['basket'])

if __name__ == '__main__':
    app.run(debug=True)
