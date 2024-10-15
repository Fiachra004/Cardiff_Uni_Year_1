from flask import Flask, render_template, request


app = Flask(__name__)

@app.route('/')
def hello():
    return "<html><body>Hello world!</body></html>"

if __name__ == '__main__':
    app.run()
