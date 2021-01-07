import React from "react";
import { Table, Button, Form, ButtonGroup, Collapse } from "react-bootstrap";
import Axios from "../../apis/Axios";

class Objekat extends React.Component {
  constructor(props) {
    super(props);

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
      poruke: [],
      zgrade: [],
      glasovi: [],
      search: { naslov: "", tip:"", zgradaId: -1 },
      searchShow: false,
      tipObavestenjeShow: false,
      tipPredlogShow: false,
      pageNum: 0,
      totalPages: 1,
    };
  }

  componentDidMount() {
    this.getData();
  }

  async getData() {
    await this.getZgrade();  
    await this.getPoruke();
  }

  async getPoruke(page = null) {
    let config = { params: {} };

    if (this.state.search.naslov != "") {
      config.params.naslov = this.state.search.naslov;
    }

    if (this.state.search.tip != "") {
      config.params.tip = this.state.search.tip;
    }

    if (this.state.search.zgradaId != -1) {
      config.params.zgradaId = this.state.search.zgradaId;
    }

    if (page != null) {
      config.params.pageNum = page;
    } else {
      config.params.pageNum = this.state.pageNum;
    }

    try {
      let result = await Axios.get("/poruke", config);
      this.setState({
          poruke: result.data,
          totalPages: result.headers["total-pages"],
        });
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
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

  goToEdit(porukaId) {
    this.props.history.push("/index/edit/" + porukaId);
  }

  goToGlasaj(porukaId) {
    this.props.history.push("/poruke/glasaj/" + porukaId);
  }

  async doAdd() {
    try {
      await Axios.post("/poruke/", this.state.poruka);

      let poruka = {
        naslov: "",
        tip: "",
        potrebanProcenat: 0,
        prihvacen: false,
        opis: "",
        zgradaId: -1,
      };

      this.setState({ poruka: poruka });
      this.getPoruke();
    } catch (error) {
      alert("Nije uspelo dodavanje.");
    }
  }

  async doDelete(porukaId) {
    try {
      await Axios.delete("/poruke/" + porukaId);
      this.getPoruke();
    } catch (error) {
      if (error.response.status === 400) {
        alert("Račun nije moguće obrisati. Stanje nije 0");
      } else
        alert("Nije uspelo brisanje.");
    }
  }

  addValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let poruka = this.state.poruka;
    poruka[name] = value;

    this.setState({ poruka: poruka });
  }

  searchValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({ search: search });
  }

  showTipPoruke(event) {
    let tipPoruke = event.target.value;

    if (tipPoruke === "predlog") {
      this.setState({tipPredlogShow: true});
    } else {
      this.setState({tipPredlogShow: false});
    }
  }

  doSearch() {
    this.setState({ totalPages: 1, pageNum: 0 });
    this.getPoruke(0);
  }

  changePage(direction) {
    let page = this.state.pageNum + direction;
    this.getPoruke(page);
    this.setState({ pageNum: page });
    //this.setState({pageNum: page}, this.getRacuni());
  }

  render() {
    return (
      <div>
        <h1>Poruke kućnog saveta</h1>

        <Form style={{marginTop:35}}>
          <Form.Group>
            <Form.Label>Naslov</Form.Label>
            <Form.Control onChange={(event) => this.addValueInputChange(event)} name="naslov" value={this.state.poruka.naslov} as="input"></Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Tip</Form.Label>
            <Form.Control onChange={event => {this.addValueInputChange(event); this.showTipPoruke(event)}} name="tip" value={this.state.poruka.tip} as="select">
              <option value={""}></option>
              <option value={"obaveštenje"} key={"obaveštenje"}>{"obaveštenje"}</option>
              <option value={"predlog"} key={"predlog"}>{"predlog"}</option>
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Opis</Form.Label>
            <Form.Control onChange={(event) => this.addValueInputChange(event)} name="opis" value={this.state.poruka.opis} as="input"></Form.Control>
          </Form.Group>
          <Collapse in={this.state.tipPredlogShow}>    
            <Form.Group>
              <Form.Label>Potreban procenat</Form.Label>
              <Form.Control onChange={(event) => this.addValueInputChange(event)} name="potrebanProcenat" value={this.state.poruka.potrebanProcenat} as="input" type="number"></Form.Control>
            </Form.Group>
          </Collapse>
          <Form.Group>
            <Form.Label>Zgrada</Form.Label>
            <Form.Control onChange= {(event) => this.addValueInputChange(event)} name="zgradaId" value={this.state.poruka.zgradaId} as="select">
              <option value={-1}></option>
              {
                this.state.zgrade.map((zgrada) => {
                  return (
                    <option value={zgrada.id} key={zgrada.id}>{zgrada.adresa}</option>
                  );
              })}
            </Form.Control>
          </Form.Group>
          <Button variant="primary" onClick={() => this.doAdd()}>Dodaj</Button>
        </Form>

        <Form style={{marginTop:35}}>
          <Form.Group controlId="formBasicCheckbox">
            <Form.Check type="checkbox" label="Prikaži formu za pretragu" 
              onClick={() => { this.setState({ searchShow: !this.state.searchShow }) }} 
              aria-controls="example-collapse-search"
              aria-expanded={this.state.searchShow}/>
          </Form.Group>
          <Collapse in={this.state.searchShow}>
              <div style={{ margin: 10 }} id="example-collapse-search">
                <Form.Group>
                  <Form.Label>Zgrada</Form.Label>
                  <Form.Control onChange={(event) => this.searchValueInputChange(event)} name="zgradaId" value={this.state.search.zgradaId} as="select">
                    <option value={-1}></option>
                    {
                      this.state.zgrade.map((zgrada) => {
                        return (
                          <option value={zgrada.id} key={zgrada.id}>{zgrada.adresa}</option>
                        );
                    })}
                  </Form.Control>
                </Form.Group>
                <Form.Group>
                  <Form.Label>Naslov</Form.Label>
                  <Form.Control value={this.state.search.naslov} name="naslov" as="input" onChange={(e) => this.searchValueInputChange(e)}></Form.Control>
                </Form.Group>
                <Form.Group>
                  <Form.Label>Tip</Form.Label>
                  <Form.Control onChange={(event) => this.searchValueInputChange(event)} name="tip" value={this.state.search.tip} as="select">
                    <option value={""}></option>
                    <option value={"obaveštenje"} key={"obaveštenje"}>{"obaveštenje"}</option>
                    <option value={"predlog"} key={"predlog"}>{"predlog"}</option>
                  </Form.Control>
                </Form.Group>
                <Button onClick={() => this.doSearch()}>Traži</Button>
              </div>
          </Collapse>
        </Form>


        <div style={{marginTop: 25, float:"right"}}>
          <ButtonGroup>
            <Button disabled={this.state.pageNum == 0} onClick={() => this.changePage(-1)}>Nazad</Button>
            <Button disabled={this.state.pageNum + 1 == this.state.totalPages} onClick={() => this.changePage(1)}>Napred</Button>
          </ButtonGroup>
        </div>
    

        <Table bordered striped style={{ marginTop: 2 }}>
          <thead className="thead-dark">
            <tr>
              <th>Naslov</th>
              <th>Tip</th>
              <th>Opis</th>
              <th>Potreban %</th>
              <th>Zgrada</th>
              <th colSpan={2}>Akcije</th>
            </tr>
          </thead>
          <tbody>
            {this.state.poruke.map((poruka) => {
              return (
                <tr key={poruka.id}>
                  <td>{poruka.naslov}</td>
                  <td>{poruka.tip}</td>
                  <td>{poruka.opis}</td>
                  <td>{poruka.potrebanProcenat}</td>
                  <td>{poruka.zgradaAdresa}</td>
                  <td>
                    <Button variant="warning" onClick={() => this.goToEdit(poruka.id)} style={{ marginLeft: 5 }}>Izmeni</Button>
                    <Button variant="danger" onClick={() => this.doDelete(poruka.id)} style={{ marginLeft: 5 }}>Obriši</Button>
                    <Collapse in={poruka.tip === "predlog"}>   
                      <Button variant="success" onClick={() => this.goToGlasaj(poruka.id)} style={{ marginLeft: 5 }}>Glasaj</Button>
                    </Collapse>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default Objekat;
