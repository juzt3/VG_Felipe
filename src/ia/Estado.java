package ia;

class Estado implements Comparable{
    
    public int x;
    public int y;
    public char oper; //'N'=nada, 'L': izquierda, 'R': derecha
    //'U': Arriba, 'D': abajo
    public Estado predecesor;
    public double f, g, h;
    
    
    public Estado(int x, int y, char oper,Estado predecesor) {
        this.x=x;
        this.y=y;
        this.oper=oper;
        this.predecesor=predecesor;
        
    }
    
    public boolean equals(Object x) {
        Estado e=(Estado)x;
        return this.x==e.x && this.y==e.y;
    }
    
    public int getY(){
    	return this.y;
    }
    
    public int getX(){
    	return this.x;
    }
        
    @Override
    public String toString() {
        return "("+x+","+y+"): G= "+this.g+" H "+this.h+" F "+this.f;
    }
    
    @Override
    public int compareTo(Object o) {
        Estado e=(Estado)o;
        if ( this.f == e.f ) return 0;
        else {
            if ( this.f > e.f ) return 1;
            else return -1;
        }
    }
}
