import React from 'react';
import './App.css';


import NavigationBar from "./componnents/NawigationBar";
import {Container, Row, Col } from "react-bootstrap";
import Welcome from "./componnents/Welcome";
import Footer from "./componnents/Footer";
import Car from "./componnents/Car";
import CarList from "./componnents/CarList";

import { Route, Switch, BrowserRouter as Router } from 'react-router-dom';

function App() {
    const marginTop = {
        marginTop: "20px"
    };

  return (
    <Router>
        <NavigationBar/>
        <Container>
            <Row>
                <Col lg={12} style={marginTop}>
                    <Switch>
                        <Route path="/" exact component={Welcome}/>
                        <Route path="/add" exact component={Car}/>
                        <Route path="/list" exact component={CarList}/>
                        </Switch>
                </Col>
            </Row>
        </Container>
        <Footer/>
        </Router>
       
  );
}

export default App;
