import {sleep} from 'k6';
import {createUser} from './gateway.js';

const users = JSON.parse(open("./src/main/resources/static/users.json"));

export const options = {
    vus: 10,
    duration: '20s',
};

export default function () {
    const user = users[__VU - 1];

//  const { body, status } = loginUser(user);
    const newUser = JSON.parse(createUser(user, 10).body);
    console.log(newUser)

//  if (status === 404) {
//    const newUser = JSON.parse(createUser(user).body);
////    const gotUser = JSON.parse(getUser(newUser.id).body);
//  }

    sleep(1);
}