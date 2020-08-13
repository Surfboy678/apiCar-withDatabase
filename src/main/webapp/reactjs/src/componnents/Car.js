import React, {Component} from 'react';
import { Card, Form, Button, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faSave, faPlusSquare} from '@fortawesome/free-solid-svg-icons'


export default  class Car extends Component{

    constructor(props){
        super(props);
        this.state = {name:'', mark:'', model:'', color:'', year:''}
        this.carChange = this.carChange.bind(this);
        this.submitCar = this.submitCar.bind(this);
    }
    submitCar(event){
        alert('name ' +this.state.name +  ',Mark ' +this.state.mark + ',model ' +this.state.model + ',color ' +this.state.color + ',year ' + this.state.year);
        event.preventDefault();
    }
    carChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render(){
    return(  
    <Card className="border border-dark bg-dark text-white">
    <Card.Header><FontAwesomeIcon icon={faPlusSquare}/> Add Car</Card.Header>
    <Form onSubmit={this.submitCar} id="carFormId">
        <Card.Body>
         <Form.Row>
             <Form.Group as={Col} controlId= "formGridName">
                 <Form.Label>Name</Form.Label>
                     <Form.Control 
                required 
                type="text"
                name="name" 
                value={this.state.name}
                 onChange={this.carChange}
                className="bg-dark text-white"
                placeholder="Enter Name Car" />
          </Form.Group>

  <Form.Group as={Col} controlId= "formGridMark"s>
        <Form.Label>Mark</Form.Label>
             <Form.Control  
             required  
             type="text"
             name="mark"
             value={this.state.mark}
             onChange={this.carChange}
            className="bg-dark text-white"
             placeholder="Mark" />
    </Form.Group>

  <Form.Group as={Col} controlId= "formGridModel">
    <Form.Label>Model</Form.Label>
    <Form.Control  
    required 
    type="text" 
    name="model"
    value={this.state.model}
      onChange={this.carChange} 
    className="bg-dark text-white" 
    placeholder="Model" />
  </Form.Group>

  <Form.Group as={Col} controlId= "formGridColor">
    <Form.Label>Color</Form.Label>
    <Form.Control  
    required 
     type="text" 
     name="color"
     value={this.state.color}
      onChange={this.carChange}
      className="bg-dark text-white" 
      placeholder="Color" />
  </Form.Group>

  <Form.Group as={Col} controlId= "formGridYear">
    <Form.Label>Year produce</Form.Label>
    <Form.Control  
    required 
    type="number" 
    name="year"
    value={this.state.year}
      onChange={this.carChange} 
    className="bg-dark text-white" 
    placeholder="Year produce" />
  </Form.Group>
            </Form.Row>
            </Card.Body>
            
        <Card.Footer style={{"textAlign": "right"}}>
        <Button size="sm" variant="success" type="submit">
        <FontAwesomeIcon icon={faSave}/> Submit
  </Button>
        </Card.Footer>
        </Form>
        </Card>

        

        );
    }

}
