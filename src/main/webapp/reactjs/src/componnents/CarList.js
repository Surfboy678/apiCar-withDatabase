import React, {Component} from 'react';
import { Card, Table, ButtonGroup, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faList, faEdit, faTrash} from '@fortawesome/free-solid-svg-icons'
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


    render() {
        return(

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
                    <Button size="sm" variant="outline-primary"><FontAwesomeIcon icon={faEdit}/></Button>
                    <Button size="sm" variant="outline-danger"><FontAwesomeIcon icon={faTrash}/></Button>
                </ButtonGroup>
            </td>
        </tr>
    ))
    }
  </tbody>
</Table>
           </Card.Body>

       </Card>
    
        );
    }

}
