import { APIServer } from "../Support/Constant"

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                window.location.reload(true);
            }
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}

export const create_model =  (model, table) => {
    let url = APIServer + `${table}`;
    url += `_create`;
    return fetch(url,{
        mode: 'cors',
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(model)
    }).then(handleResponse);
}

export const getAll = (table) => {
    let url = APIServer + `${table}`;
    url += `_getall`;
    return fetch(url,{
        mode: 'cors',
        method: 'GET', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
        },
    }).then(handleResponse);
}

export const search = (model, table) => {
    let url = APIServer + `${table}`;
    url += `_search`;
    return fetch(url,{
        mode: 'cors',
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(model),
    }).then(handleResponse);
}

export const find_model = (model, table) => {
    let url = APIServer + `${table}`;
    url += `_find`;
    return fetch(url,{
        mode: 'cors',
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(`${model}`.split(';')),
    }).then(handleResponse);
}

export const update_model = (model, table) => {
    let url = APIServer + `${table}`;
    url += `_update`;
    return fetch(url,{
        mode: 'cors',
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(model),
    }).then(handleResponse);
}

export const delete_model = (model, table) => {
    let url = APIServer + `${table}`;
    url += `_delete`;
    return fetch(url,{
        mode: 'cors',
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(model),
    }).then(handleResponse);
}

export const getLastIndex = (table) => {
    let url = APIServer + `${table}`;
    url += "_getlastindex";
    return fetch(url,{
        mode: 'cors',
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json'
        },
    }).then(handleResponse);
}