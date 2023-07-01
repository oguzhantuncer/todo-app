import React, { Component } from 'react';
import service from '../service';

export default class TodoItem extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.id,
      name: this.props.name,
      isCompleted: this.props.status,
      isChecked: this.props.status === 'COMPLETED' ? true : false,
      isEditable: false,
      content: this.props.name,
    };
  }

  handleClick = async () => {
    await this.setState(prevState => ({
      isChecked: !prevState.isChecked,
      isCompleted: prevState.isCompleted === 'COMPLETED' ? 'NOT_COMPLETED' : 'COMPLETED',
    }));

    service.update(this.state.id, { name: this.state.name, status: this.state.isCompleted });
  };

  handleEdit = async () => {
    await this.setState(prevState => ({
      isEditable: !prevState.isEditable,
    }));
  };

  handleUpdate = async () => {
    await this.setState(prevState => ({
      isEditable: !prevState.isEditable,
      name: prevState.content,
    }));

    service.update(this.state.id, { name: this.state.name, status: this.state.isCompleted });
  };

  handleDelete = async () => {
    service
      .delete(this.state.id)
      .then(response => {
        console.info(response.status);
        window.location.reload();
      })
      .catch(error => {
        console.error(error);
      });
  };

  handleChange = (event) => {
    this.setState({ content: event.target.innerText });
  };

  render() {
    return (
      <li className="list-group-item d-flex my-3">
        <div class="col-2">
          <h6
            className="mt-1 mb-0 align-left"
            style={{
              textDecoration: this.state.isChecked ? 'line-through' : 'none',
            }}
            contentEditable={this.state.isEditable}
            onInput={this.handleChange} // onChange yerine onInput kullan
          >
            {this.state.name}
          </h6>
        </div>
        <div class="col-1">
          <button
            style={{
              visibility: this.state.isEditable ? 'visible' : 'hidden',
            }}
            type="button"
            class="btn btn-success"
            onClick={this.handleUpdate}
          >
            Update
          </button>
        </div>
        <div class="col-9 d-grid gap-2 d-md-flex justify-content-md-end">
          <div className="todo-icon ">
            <span className="mx-2">
              <input
                className="form-check-input"
                type="checkbox"
                checked={this.state.isChecked}
                onChange={this.handleClick}
              />
            </span>
            <span className="mx-2 text-warning" onClick={this.handleEdit}>
              <i className="fas fa-pen" />
            </span>
            <span className="mx-2 text-danger" onClick={this.handleDelete}>
              <i className="fas fa-trash" />
            </span>
          </div>
        </div>
      </li>
    );
  }
}
