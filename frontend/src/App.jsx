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
      console.error(error);
    }
  }

  const handleDeleteCompletedButton = async () => {
    try {
      await service.deleteCompleted()

      service.getAll()
        .then(response => {
          setTodos(response.data);
        })
        .catch(error => {
          console.error(error);
        });
      setTodo('');
    } catch (error) {
      console.error(error);
    }
  }

  const handleDeleteAllCompletedButton = async () => {
    try {
      await service.deleteAll()

      service.getAll()
        .then(response => {
          setTodos(response.data);
        })
        .catch(error => {
          console.error(error);
        });
      setTodo('');
    } catch (error) {
      console.error(error);
    }
  }

  const handleGetAllButton = async () => {
    service.getAll()
    .then(response => {
      setTodos(response.data);
    })
    .catch(error => {
      console.error(error);
    });
  }

  const handleGetAllCompleted = async () => {
    service.getAllCompleted()
    .then(response => {
      setTodos(response.data);
    })
    .catch(error => {
      console.error(error);
    });
  }

  const handleGetAllNotCompleted = async () => {
    service.getAllNotCompleted()
    .then(response => {
      setTodos(response.data);
    })
    .catch(error => {
      console.error(error);
    });
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
            <h2 className="mt-5" >TodoInput</h2>
            <div className="input-group mb-3">
              <span className="input-group-text" id="basic-addon1">@</span>
              <input type="text" className="form-control" placeholder="New Todo" value={todo} onChange={handleInputChange} />
            </div>
            <div className="d-grid gap-2">
              <button onClick={addNewTodo} className="btn btn-info" type="button">Add New Task</button>
            </div>
          </div>
        </div>        
        <div className="row">
          <div className="col">
            <h2 className="mt-5" >TodoList</h2>

            <div className="row mt-3">
              <div className='d-grid gap-2 col-4 mx-auto'>
                <button class="btn btn-primary" type="button" onClick={handleGetAllButton}>All</button>
              </div>
              <div className='d-grid gap-2 col-4 mx-auto'>
                <button class="btn btn-primary" type="button" onClick={handleGetAllCompleted}>Done</button>
              </div>
              <div className='d-grid gap-2 col-4 mx-auto'>
                <button class="btn btn-primary" type="button" onClick={handleGetAllNotCompleted}>Todo</button>
              </div>
            </div>
            <ul className="list-group">
              {listItems}
            </ul>
          </div>
        </div>
        <div className='row'>
          <div className='d-grid gap-2 col-6 mx-auto'>
            <button class="btn btn-danger" type="button" onClick={handleDeleteCompletedButton}>Delete done tasks</button>
          </div>
          <div className='d-grid gap-2 col-6 mx-auto'>
            <button class="btn btn-danger" type="button" onClick={handleDeleteAllCompletedButton}>Delete all tasks</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;