export const Button = ({ style, label, ...props }) => {
    return <button style={style} {...props}>{label}</button>
}