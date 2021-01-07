import React from "react";
import ReactDOM from "react-dom";
import { Route, Link, HashRouter as Router, Switch, Redirect } from "react-router-dom";
import Home from "./components/Home";
import Objekat from "./components/objekti/Objekat";
import EditObjekat from "./components/objekti/EditObjekat";
import Glasaj from "./components/objekti/Glasaj";
import NotFound from "./components/NotFound";

import { Navbar, Nav, Container, Button } from "react-bootstrap";
import Login from "./components/authentication/Login";
import {logout} from './services/auth';

class App extends React.Component {
  render() {

    let token = window.localStorage.getItem("token");

    if(token){
      return (
        <div>
          <Router>
            <Navbar bg="dark" variant="dark" expand fixed="top">
              <Navbar.Brand>
                <Link to="/">JWD</Link>
              </Navbar.Brand>
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/index">
                  Poruke kućnog saveta
                </Nav.Link>
              </Nav>
              <Button onClick={()=>logout()}>Logout</Button>
            </Navbar>
  
            <Container style={{marginTop:75}}>
              <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/index" component={Objekat} />
                <Route exact path="/index/edit/:id" component={EditObjekat} />
                <Route exact path="/poruke/glasaj/:id" component={Glasaj} />
                <Route exact path="/login" render={()=><Redirect to="/" />} />
                <Route component={NotFound} />
              </Switch>
            </Container>
          </Router>
        </div>
      );
    }else{

      return (
        <Container>
          <Router>
            <Switch>
              <Route exact path="/login" component={Login} />
              <Route render={()=><Redirect to="/login" />} />
            </Switch>
          </Router>
        </Container>
      );

    }

    
  }
}

ReactDOM.render(<App />, document.querySelector("#root"));
