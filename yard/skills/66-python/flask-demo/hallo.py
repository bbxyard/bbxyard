from flask import Flask;

app = Flask(__name__)

@app.route('/')
def index():
  return "<h1>Just do it</h1>"

@app.route('/hallo')
def hallo():
  return "<h2>hallo welt</h2>"

@app.route('/hello')
def hello():
  return "<h2>hello world</h2>"

if __name__ == '__main__':
  app.run()
