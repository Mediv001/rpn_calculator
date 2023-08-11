export const EquationList = ({data, onSelect}) => {

    const onClick = (uid) => () => {
        onSelect(uid)
    }

    return <ul>
        {data.map(({uid, equation}) => (<li onClick={onClick(uid)} id={uid}>{equation}</li>))}
    </ul>
}