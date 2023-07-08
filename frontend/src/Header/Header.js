
import { NavLink } from 'react-router-dom';
import './Header.css';

function Header() {
  return (
    <header className="header">
        <div className="header__info">
            <p>Добро пожаловать, James</p>
            <span>1 October  2022 | 11:59 AM GMT</span>
        </div>
        <div className="header__user">
            <div className="button"><NavLink to="./Login">Log In</NavLink></div>
            <div className="button button__red"><NavLink to="./registration/Registration">Sign In</NavLink></div>
        </div>
    </header>
  );
}

export default Header;
