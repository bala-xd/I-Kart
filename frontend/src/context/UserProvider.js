import React, { createContext, useEffect, useState } from 'react';
import auth from '../modules/Auth';
import { jwtDecode } from 'jwt-decode';

export const UserContext = createContext();

function UserProvider({ children }) {
    const [user, setUser] = useState();

    useEffect(() => {
        const token = auth.getToken();
        if (token && token.length > 0) {
            const decodedUser = jwtDecode(token);
            setUser(decodedUser);
        }
    }, []);

    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
}

export default UserProvider;