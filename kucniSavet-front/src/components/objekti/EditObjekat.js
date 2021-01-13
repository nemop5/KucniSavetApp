import React from 'react';
import Axios from '../../apis/Axios';
import { Button, Form, Collapse } from 'react-bootstrap';

class EditObjekat extends React.Component{

    constructor(props){
        super(props);

        let objekat = {
            naslov: "",
            tip: "",
            potrebanProcenat: "",
            prihvacen: false,
            opis: "",
            zgradaId: -1,
          };

        this.state = {
            objekat: objekat, 
            zgrade: [],
            tipPredlogShow: false,
        }; 
    }

    componentDidMount(){
        this.getPoruka(this.props.match.params.id);
        this.getZgrade();
    }

    async getPoruka(porukaId){
        try{
            let result = await Axios.get("/poruke/" + porukaId);
            console.log(result.data);
            this.setState({objekat: result.data});
        }catch(error){
            console.log(error);
            alert("Nije uspelo dobavljanje poruke!");
        }
    }

    async getZgrade() {
        try {
          let result = await Axios.get("/zgrade");
          this.setState({zgrade: result.data});
        } catch (error) {
          alert("Nije uspelo dobavljanje.");
        }
    }

    async edit(){
        try{
            await Axios.put("/poruke/" + this.props.match.params.id, this.state.objekat);
            this.props.history.push("/index");
            alert("Poruka je uspešno izmenjena!")
        }catch(error){
            console.log(error);
            alert("Nije uspelo čuvanje!");
        }
    }

    ValueInputChange(e){
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let objekat = this.state.objekat;
        objekat[name] = value;

        this.setState({objekat: objekat});
    } 

    showTipPoruke(event) {
        let objekat = this.state.objekat;
        let tipPoruke = event.target.value;

        if (tipPoruke === "predlog") {
          this.setState({tipPredlogShow: true});
        } else {
          objekat["potrebanProcenat"] = 0;
          this.setState({tipPredlogShow: false, objekat: objekat});
        }
      }

    render(){
        return (
            <div>
                <h1>Izmena poruke</h1>

                <Form style={{marginTop:35}}>
                    <Form.Group>
                        <Form.Label>Naslov</Form.Label>
                        <Form.Control onChange={(event) => this.ValueInputChange(event)} name="naslov" value={this.state.objekat.naslov} as="input"></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Tip</Form.Label>
                        <Form.Control onChange={event => {this.ValueInputChange(event); this.showTipPoruke(event)}} name="tip" value={this.state.objekat.tip} as="select">
                            <option value={""}></option>
                            <option value={"obavestenje"} key={"obavestenje"}>{"obavestenje"}</option>
                            <option value={"predlog"} key={"predlog"}>{"predlog"}</option>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Opis</Form.Label>
                         <Form.Control onChange={(event) => this.ValueInputChange(event)} name="opis" value={this.state.objekat.opis} as="input"></Form.Control>
                    </Form.Group>
                    <Collapse in={this.state.tipPredlogShow}>    
                        <Form.Group>
                            <Form.Label>Potreban procenat</Form.Label>
                            <Form.Control onChange={(event) => this.ValueInputChange(event)} name="potrebanProcenat" value={this.state.objekat.potrebanProcenat} as="input" type="number"></Form.Control>
                        </Form.Group>
                    </Collapse>
                    <Form.Group>
                        <Form.Label>Zgrada</Form.Label>
                        <Form.Control onChange= {(event) => this.ValueInputChange(event)} name="zgradaId" value={this.state.objekat.zgradaId} as="select">
                        <option value={-1}></option>
                        {
                            this.state.zgrade.map((zgrada) => {
                                return (
                                    <option value={zgrada.id} key={zgrada.id}>{zgrada.adresa}</option>
                                );
                        })}
                        </Form.Control>
                    </Form.Group>
                    <Button onClick={()=> this.edit()}>Edit</Button>
                </Form>
            </div>
        )
    }
}

export default EditObjekat;