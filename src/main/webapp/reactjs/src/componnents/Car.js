import React, {Component} from 'react';
import { Card, Form, Button, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faSave, faPlusSquare, faUndo} from '@fortawesome/free-solid-svg-icons'
import MyToast from './MyToast'
import axios from 'axios';


export default  class Car extends Component{

    constructor(props){
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.carChange = this.carChange.bind(this);
        this.submitCar = this.submitCar.bind(this);
    }

    initialState = {
      carId:'', name:'', mark:'', model:'', color:'', dataProduce:''
    }

    resetCar=() =>{
      this.setState(() => this.initialState);
    }

    submitCar = event =>{
        event.preventDefault();

        const car = {
        carId: this.state.carId,
        name: this.state.name,
        mark: this.state.mark,
        model: this.state.model,
        color: this.state.color,
        dataProduce: this.state.dataProduce

        };

        axios.post("http://localhost:8080/cars/save", car)
        .then(response => {
            if(response.data != null){
            this.setState({"show" : true});
            setTimeout(() => this.setState({"show" : false}), 3000 )
            }else{
              this.setState({"show" : false});
            }
        }); 
        this.setState(this.initialState);
    }
      

    carChange=event =>{
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render(){

      const {carId, name, mark, model, color, dataProduce} = this.state;

    return(
      <div>
        <div style={{"display": this.state.show ? "block": "none"}}>
          <MyToast children = {{show:this.state.show, message :"add car sucessfully"}}/>
        </div>
        <Card className="border border-dark bg-dark text-white">
    <Card.Header><FontAwesomeIcon icon={faPlusSquare}/> Add Car</Card.Header>
    <Form onReset={this.resetCar} onSubmit={this.submitCar} id="carFormId">
        <Card.Body>
         <Form.Row>
         <Form.Group as={Col} controlId= "formGridId">
               
               <Form.Label>carId</Form.Label>
                   <Form.Control 
              required autoComplete="off"
              type="number"
              name="carId" 
              value={carId} onChange={this.carChange}
              className="bg-dark text-white"
              placeholder="Enter id" />
        </Form.Group>


             <Form.Group as={Col} controlId= "formGridName">
               
                 <Form.Label>Name</Form.Label>
                     <Form.Control 
                required autoComplete="off"
                type="text"
                name="name" 
                value={name} onChange={this.carChange}
                className="bg-dark text-white"
                placeholder="Enter Name Car" />
          </Form.Group>

  <Form.Group as={Col} controlId= "formGridMark"s>
        <Form.Label>Mark</Form.Label>
             <Form.Control  
             required  autoComplete="off"
             type="text"
             name="mark"
             value={mark} onChange={this.carChange}
            className="bg-dark text-white"
             placeholder="Mark" />
    </Form.Group>

  <Form.Group as={Col} controlId= "formGridModel">
    <Form.Label>Model</Form.Label>
    <Form.Control  
    required autoComplete="off"
    type="text" 
    name="model"
    value={model} onChange={this.carChange} 
    className="bg-dark text-white" 
    placeholder="Model" />
  </Form.Group>

  <Form.Group as={Col} controlId= "formGridColor">
    <Form.Label>Color</Form.Label>
    <Form.Control  
    required autoComplete="off"
     type="text" 
     name="color"
     value={color} onChange={this.carChange}
      className="bg-dark text-white" 
      placeholder="Color" />
  </Form.Group>

  <Form.Group as={Col} controlId= "formGridYear">
    <Form.Label>Year produce</Form.Label>
    <Form.Control  
    required autoComplete="off"
    type="number" 
    name="dataProduce"
    value={dataProduce} onChange={this.carChange} 
    className="bg-dark text-white" 
    placeholder="Year produce" />
  </Form.Group>
            </Form.Row>
            </Card.Body>
            
      <Card.Footer style={{"textAlign": "right"}}>
        <Button size="sm" variant="success" type="submit">
                <FontAwesomeIcon icon={faSave}/> Submit
        </Button>{' '}
        <Button size="sm" variant="info" type="reset">
                <FontAwesomeIcon icon={faUndo}/> Reset
        </Button>
      </Card.Footer>
        </Form>
        </Card>
      </div>  
        
        );
    }

}
