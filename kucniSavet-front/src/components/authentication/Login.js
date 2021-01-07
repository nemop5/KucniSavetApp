import React from "react";
import {login} from '../../services/auth';
import {Jumbotron, Form, Button} from 'react-bootstrap';

class Login extends React.Component {
  constructor() {
    super();

    this.state = { username: "", password: "" };
  }

  valueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let change = {};
    change[name] = value;
    this.setState(change);
  }

  doLogin(e){
    e.preventDefault();

    login(this.state.username, this.state.password);
  }

  // TODO: Ulepšati: - centrirati, udaljiti od vrha, staviti jumbotron
  // TODO: Završiti implementaciju
  render() {
    return (
      <div className="jumbotron text-center">
        <Jumbotron>
          <h1 style={{textAlign:'center'}}>Welcome!</h1>
          <Form>
          <Form.Group>
            <Form.Label htmlFor="username">Username</Form.Label>
            <Form.Control style={{textAlign:'center'}} name="username" onChange={(e) => {this.valueInputChange(e);}}/>          
          </Form.Group>
          <Form.Group>
            <Form.Label htmlFor="password">Password</Form.Label>
            <Form.Control style={{textAlign:'center'}} name="password" type="password"  onChange={(e) => {this.valueInputChange(e);}}/>
          </Form.Group>
          <Button onClick={(e) => {this.doLogin(e);}}>Log in</Button>
          </Form>
        </Jumbotron>
      </div>
    );
  }
}

export default Login;
