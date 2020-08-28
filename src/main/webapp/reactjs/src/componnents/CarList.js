import React, {Component} from 'react';
import { Card, Table, ButtonGroup, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faList, faEdit, faTrash} from '@fortawesome/free-solid-svg-icons'
import MyToast from './MyToast'
import { Link } from 'react-router-dom';
import axios from 'axios';

export default class CarList extends Component{

    constructor(props){
        super(props);
        this.state = {
            cars : []

        };

    }

    componentDidMount(){
       this.findAllCars();

    }

    findAllCars(){
        axios.get("http://localhost:8080/cars")
        .then(Response => Response.data)
          .then((data) => {
              this.setState({cars : data});
          });

    }

    deleteCar = (carId) => {
       axios.post("http://localhost:8080/cars/delete/" + carId)
       .then(response => {
           if(response.data != null){
            this.setState({"show" : true});
            setTimeout(() => this.setState({"show" : false}), 3000 )
               this.setState({
                   cars: this.state.cars.filter(car => car.carId !== carId)

               });
            }else{
                this.setState({"show" : false});
              }

       });
    };


    render() {
        return(
            <div>
        <div style={{"display": this.state.show ? "block": "none"}}>
          <MyToast show = {this.state.show} message = {"delete car sucessfully."} type = {"danger"}/>
        </div>
        <Card className="border border-dark bg-dark text-white">
           <Card.Header><FontAwesomeIcon icon={faList}/> Car List</Card.Header>
           <Card.Body>
           <Table striped bordered hover variant="dark">
  <thead>
    <tr>
      <th>Name</th>
      <th>Mark</th>
      <th>Model</th>
      <th>Color</th>
      <th>Data Produce</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
  {this.state.cars.length === 0 ?
    <tr align="center">
        <td colSpan="6">No Cars Avalible</td>
    </tr> :
    this.state.cars.map((car) => (
        <tr key={car.id}>
            <td>{car.name}</td>
            <td>{car.mark}</td>
            <td>{car.model}</td>
            <td>{car.color}</td>
            <td>{car.dataProduce}</td>
            <td>
                <ButtonGroup>
                <Link to={"edit/" + car.carId} className="btn btn-sm btn-outline-primary" ><FontAwesomeIcon icon={faEdit}/></Link>{' '}
                    <Button size="sm" variant="outline-danger" onClick={this.deleteCar.bind(this, car.carId)}><FontAwesomeIcon icon={faTrash}/></Button>
                </ButtonGroup>
            </td>
        </tr>
    ))
    }
  </tbody>
</Table>
           </Card.Body>

       </Card>

            </div>

       
    
        );
    }

}
