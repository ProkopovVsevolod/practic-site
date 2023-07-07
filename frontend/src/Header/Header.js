import './Header.css';

function Header() {
  return (
    <header className="header">
        <div className="header__info">
            <p>Добро пожаловать, James</p>
            <span>1 October  2022 | 11:59 AM GMT</span>
        </div>
        <div className="header__user">
            <a href="./log-in.html" className="button">Log In</a>
            <a href="./sign-in.html" className="button button__red">Sign In</a>
        </div>
    </header>
  );
}

export default Header;
