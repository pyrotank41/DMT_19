from flask import Flask, request
import getData
app = Flask(__name__)

def replaceChar(string, ch):
    return string.replace(ch, ' ')

@app.route('/')
def index():
    return 'Server Works yo!'


@app.route('/greet')
def say_hello():
    return 'Hello from Server'

#get request for name.
@app.route('/pn/<name>')
def propertyName(name = None):
    name = replaceChar(name, '_')
    return getData.getFromName(name)

#get request for location.
@app.route('/pl/<location>')
def propertyLocation(location = None):
    location =  replaceChar(location, "_")
    return getData.getFromAdress(location)

#get request for id.
@app.route('/pid/<id>')
def propertyID(id = None):
    return getData.getID(id)