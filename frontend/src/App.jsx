import React, { useState, useEffect } from 'react';
import service from './service'

import TodoItem from './components/TodoItem';

import './App.css';

function App() {

  const [todo, setTodo] = useState('');
  const [todos, setTodos] = useState([]);

  useEffect(() => {
    service.getAll()
      .then(response => {
        setTodos(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  const handleInputChange = (event) => {
    setTodo(event.target.value);
  };

  const addNewTodo = async () => {
    try {
      await service.create({name:todo})

      service.getAll()
        .then(response => {
          setTodos(response.data);
        })
        .catch(error => {
          console.error(error);
        });
      setTodo('');
    } catch (error) {
      console.error("Istek hatasi", error);
    }
  }

  const listItems = todos.map(todo =>
    <TodoItem
      key={todo.id}
      id={todo.id}
      name={todo.name}
      status={todo.status}
    />
  );

  return (
    <div className="App">
      <div className="container">
        <div className="row">
          <div className="col">
            <h2>TodoInput</h2>
            <div className="input-group mb-3">
              <span className="input-group-text" id="basic-addon1">@</span>
              <input type="text" className="form-control" placeholder="New Todo" value={todo} onChange={handleInputChange} />
            </div>
            <div className="d-grid gap-2">
              <button onClick={addNewTodo} className="btn btn-primary" type="button">Add New Task</button>
            </div>
            <h2>TodoList</h2>
            <ul className="list-group">
              {listItems}
            </ul>
          </div>
        </div>
        <div className='row'>
          <div className='d-grid gap-2 col-6 mx-auto'>
            <button class="btn btn-danger" type="button">Delete done tasks</button>
          </div>
          <div className='d-grid gap-2 col-6 mx-auto'>
            <button class="btn btn-danger" type="button">Delete all tasks</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;