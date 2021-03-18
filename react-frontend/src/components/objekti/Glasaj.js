import React from 'react';
import Axios from '../../apis/Axios';
import { Button, ButtonGroup, Form } from 'react-bootstrap';

class NalogZaPrenos extends React.Component{

    constructor(props){
        super(props);

        let glas = {
            predlogPodrzan: "",
            komentar: "",
            porukaId: -1,
        }

        let poruka = {
            naslov: "",
            tip: "",
            potrebanProcenat: 0,
            prihvacen: false,
            opis: "",
            zgradaId: -1,
          };

        this.state = {
            poruka: poruka,
            glas: glas,
        }; 
    }

    componentDidMount(){
        this.getPoruka(this.props.match.params.id);
    }

    async getPoruka(porukaId){
        let glas = this.state.glas;
        try{
            let result = await Axios.get("/poruke/" + porukaId);
            console.log(result.data);
            glas["porukaId"] = porukaId;
            this.setState({poruka: result.data, glas: glas});
        }catch(error){
            console.log(error);
            alert("Nije uspelo dobavljanje poruke!");
        }
    }

    async glasaj(predlogPodrzan){
        let glas = this.state.glas;
        glas["predlogPodrzan"] = predlogPodrzan;
        this.setState({glas: glas});

        try {
            await Axios.post("/glasovi/", this.state.glas);
      
            let glas = {
                predlogPodrzan: "",
                komentar: "",
                porukaId: -1,
            };
      
            this.setState({ glas: glas});
            this.props.history.push("/index");
            alert("Vaš glas je uspešno zabeležen!")
          } catch (error) {
            alert("Glasanje nije uspelo!");
          }
    }

    ValueInputChange(event){
        let control = event.target;

        let name = control.name;
        let value = control.value;
    
        let glas = this.state.glas;
        glas[name] = value;
    
        this.setState({ glas: glas });
    }

    render(){
        return (
            <div>
                <h1>Glasanje za predlog</h1>
                <p>Naslov: {this.state.poruka.naslov}</p>
                <p>Opis: {this.state.poruka.opis}</p>
               
                <Form style={{marginTop:35}}>
                    <Form.Group>
                        <Form.Label>Komentar</Form.Label>
                        <Form.Control onChange={(event) => this.ValueInputChange(event)} name="komentar" as="input"></Form.Control>
                    </Form.Group>
                    <ButtonGroup>
                        <Button variant="success" onClick={()=> this.glasaj("ZA")} style={{width: 150, marginLeft: 5 }}>Za</Button>
                        <Button variant="danger" onClick={()=> this.glasaj("PROTIV")} style={{ width: 150, marginLeft: 5 }}>Protiv</Button>
                    </ButtonGroup>  
                </Form>
            </div>
        )
    }
}

export default NalogZaPrenos;