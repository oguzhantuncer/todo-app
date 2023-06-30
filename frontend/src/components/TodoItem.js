import React, { Component } from 'react';
import service from '../service'

export default class TodoItem extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.id,
      name: this.props.name,
      isCompleted: this.props.status,
      isChecked: this.props.status === 'COMPLETED' ? true : false,
    };
  }

  handleClick = async () => {
    await this.setState(prevState => ({
      isChecked: !prevState.isChecked,
      isCompleted: prevState.isCompleted === 'COMPLETED' ? 'NOT_COMPLETED' : 'COMPLETED',
    }));

    service.update(this.state.id, {name: this.state.name, status: this.state.isCompleted})
  };

  handleDelete = async () => {
    service.delete(this.state.id)
    .then(response => {
      console.info(response.status)
      window.location.reload()
    })
    .catch(error => {
      console.error(error);
    });
    
  };

  render() {
    return (
      <li className="list-group-item d-flex justify-content-between my-3">
        <h6
          className="mt-1 mb-0 align-middle"
          style={{
            textDecoration: this.state.isChecked ? 'line-through' : 'none',
          }}
        >
          {this.state.name} - {this.state.isChecked.toString()} - {this.state.isCompleted}
        </h6>
        <div className="todo-icon">
          <span className="mx-2">
            <input
              className="form-check-input"
              type="checkbox"
              checked={this.state.isChecked}
              onChange={this.handleClick}
            />
          </span>
          <span className="mx-2 text-warning">
            <i className="fas fa-pen" />
          </span>
          <span className="mx-2 text-danger"
                onClick={this.handleDelete}>
            <i className="fas fa-trash" />
          </span>
        </div>
      </li>
    );
  }
}