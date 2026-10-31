--1497 tokens

frame=0
function _init()
	l_level=100
	l_drain=0.7
	l_on=true
	debug={}
	particles={}
	
	puffvel={up_impulse(60,0.7)}
	firevel={up_impulse(20,0.5)}
	cravel={up_impulse(70,1.2)}
	
	torch={
		pos={90,70},
		conf={
			lo={
				n=1,
				p={
					{{puff,3},60,0.7},
					{{fire,1},20,0.5}
				}
			},
			mi={
				n=2,
				p={
					{{fire,4},20,0.5},
					{{puff,1},60,0.7},
					{{crackle,1},70,1.2}
				}
			},
			hi={
				n=2,
				p={
					{{fire,5},20,0.5},
					{{crackle,1},70,1.2}
				}
			}
		}
	}
end

function _update()
	frame+=1
	if btnp(⬆️) then
		l_level=mid(0,l_level+10,100)
	end
	if (btnp(⬇️)) stop("down")
	if (btnp(❎)) l_on=not l_on
	if (btnp(🅾️)) stop("c")
	
	if frame%4==0 then
		pp=spwn_parti(puff,13,60,0,0,{up_impulse(60,0.7)}) 
		f=spwn_parti(fire,13,95,0,0,{up_impulse(20,0.5)})
		cra=spwn_parti(crackle,13,120,0,0,{up_impulse(70,1.2)})
	end
	
	if (frame%7==0) then
		run_torch()
	end
	
	update_particles()
	if (l_level-l_drain>0 and l_on) l_level-=l_drain
	debug[1]=flr(l_level)
end

function _draw()
	cls(0)
	cursor(4,4)
	color(7)
	for txt in all(debug) do
		print(txt)
	end
--	debug[2]=stat(1)
	draw_parti()
	d_torchlife(15,4,l_level/100)
end

function update_particles()
	for p in all(particles) do	p:update()	end
end
function draw_parti()
	for p in all(particles) do
		p:draw()
	end
end

function d_torchlife(x0,y0,prog)
	fillp(▒)
	rectfill(x0,y0,x0+100,y0+5,1)
	fillp()
	local size=100*prog
	if (size>=1) rectfill(x0,y0,x0+size,y0+5,9)
end
function getlightstage()
	local ll=l_level
	if (ll>75) return "hi"
	if (ll>=30) return "mi"
	return "lo"
end

function run_torch()
--	if (not l_on) return
	local s=getlightstage()
	debug[2]=s
	local s_conf=torch.conf[s]
	local pwei=s_conf.p
	if not s_conf.tw then
		local tot_wei=0
		for pw in all(pwei) do
			tot_wei+=pw[1][2]
		end
		s_conf.tw=tot_wei
	end
	for i=1,s_conf.n do
		local pi=pick_one(pwei,s_conf.tw)
		local sel_p=pwei[pi]
		local vel={up_impulse(sel_p[2],sel_p[3])}
		spwn_parti(sel_p[1][1],80,80,0,0,vel)
	end
end

--------------------------

--helpers
function tween(a,b,t,e)
	local progress=t
	if (e) progress=e(progress)
	--𝘭𝘪𝘯𝘦𝘢𝘳 𝘱𝘳𝘰𝘨𝘳𝘦𝘴𝘴 𝘸/ 𝘯𝘰 𝘦𝘢𝘴𝘪𝘯𝘨
	return lerp(a,b,progress)
end
function lerp(a,b,t)
	return a+(b-a)*t
end
function e_outcirc(t)
	return sqrt(1-pow((t-1),2))
end
function pow(n,p)
	local result=1
	for i=1,p or 1 do
		result*=n
	end
	return result
end

function rnd_range(x) -- x to -x
	if (coinflip()) return -1*rnd(x)
	return rnd(x)
end
function rnd_betwn(low,high)
	return flr(rnd(high-low+1)+low)
end
function coinflip()
	return rnd(1)>=.5
end

function pick_one(sel,totw)
	roll = rnd(totw)
	acc = 0
	for i=1,#sel do
		--simplify w/ ipairs?
		pw=sel[i][1]
		acc+=pw[2]
		if acc>=roll then
			return i
		end
	end
end

function tot_w(sel)
	local tw = 0
	for particle in all(sel) do
		tw+=particle[1][2]
	end
	return tw
end

function shade(c)
 return c_shades[c+1]
end
c_shades={
0,1,5,1,
2,1,5,6,
2,4,9,3,
1,5,3,4
}

------------------

--particles

function spwn_parti(parti,x0,y0,xo,yo,xtr_behav)
--	local p=parti:new() --p = {} com __index = puff
	local p=parti:new({
	x=x0,
	y=y0,
	xoff=xo or 0,
	yoff=yo or 0,
	behav={unpack(parti.behav)}
	})
	--𝘶𝘯𝘱𝘢𝘤𝘬 𝘦𝘯𝘷𝘰𝘭𝘵𝘰 𝘥𝘦 {} 𝘤𝘳𝘪𝘢 𝘶𝘮𝘢
	--𝘭𝘪𝘴𝘵𝘢 𝘤𝘰𝘮 𝘰𝘴 𝘷𝘢𝘭𝘰𝘳𝘦𝘴 𝘤𝘢𝘴𝘰 𝘤𝘰𝘯-
	--𝘵𝘳𝘢𝘳𝘪𝘰 𝘱𝘦𝘨𝘢 𝘴𝘰 𝘰 𝘱𝘳𝘪𝘮𝘦𝘪𝘳𝘰
	
	--𝘤𝘳𝘪𝘢 𝘶𝘮𝘢 𝘤𝘰𝘱𝘪𝘢 𝘳𝘢𝘴𝘢 𝘥𝘢 𝘵𝘢𝘣𝘦𝘭𝘢
	--𝘥𝘦 𝘣𝘦𝘩𝘢𝘷𝘪𝘰𝘳𝘴 𝘦𝘮 '𝘱𝘢𝘳𝘵𝘪', 𝘮𝘦𝘴𝘮𝘰
	--𝘲𝘶𝘦 𝘷𝘢𝘻𝘪𝘢. 𝘢 𝘪𝘯𝘴𝘵𝘢𝘯𝘤𝘪𝘢 '𝘱'
	--𝘱𝘢𝘴𝘴𝘢 𝘢 𝘵𝘦𝘳 𝘶𝘮𝘢 𝘵𝘢𝘣𝘦𝘭𝘢
	--𝘱𝘳𝘰𝘱𝘳𝘪𝘢 𝘦 𝘪𝘨𝘶𝘢𝘭 𝘢 𝘰𝘳𝘪𝘨𝘪𝘯𝘢𝘭.
	--𝘪𝘴𝘴𝘰 𝘱𝘦𝘳𝘮𝘪𝘵𝘦 𝘰 𝘤𝘰𝘮𝘱𝘰𝘳𝘵𝘢𝘮𝘦𝘯𝘵𝘰
	--𝘤𝘰𝘳𝘳𝘦𝘵𝘰 𝘥𝘦 𝘪𝘯𝘴𝘵𝘢𝘯𝘤𝘪𝘢 𝘢𝘰 𝘵𝘦𝘯𝘵𝘢𝘳
	--𝘮𝘰𝘥𝘪𝘧𝘪𝘤𝘢𝘳 '𝘱', 𝘢𝘥𝘪𝘤𝘪𝘰𝘯𝘢𝘯𝘥𝘰
	--𝘯𝘰𝘷𝘰𝘴 𝘤𝘰𝘮𝘱𝘰𝘳𝘵𝘢𝘮𝘦𝘯𝘵𝘰𝘴
	if (xtr_behav) for b in all(xtr_behav) do add(p.behav,b) end
	
	--initializing any state
	--before spawning
	p:init()
	add(particles,p)
	return p
end

--behaviors
--rendering behav
function r_circ(r,c,shd,pat,i)
	local rkey="r"..(i or "")
	return {
		init=function(p)
			p[rkey]=r
			p.c=c
		end,
		drw=function(p)
			local cc=p.c
			local cr=p[rkey]
			if shd do
				fillp(0b1000101010111110)
				circfill(p.x,p.y+1,cr,cc+16*shade(cc))
				fillp()
			end
			if (pat) fillp(pat)
			circfill(p.x,p.y,cr,cc)
		end
	}
end

function r_line(size)
		return {
		drw=function(p)
			angledline(atan2(p.vx,p.vy),size,
			p.x,p.y,p.c)
		end
	}
end
function angledline(a,size,ox,oy,c)
	line(ox,oy,ox+cos(a)*size,oy+sin(a)*size,c)
end

--update behav
function b_drag(dx,dy)
	return {upd=function(p)
		if (p.vx and dx) p.vx*=dx
		if (p.vy and dy) p.vy*=dy
	end}
end

function b_osci(prop,ampl,mag)
	local pkey="ph_"..prop
	return {
		init=function(p) p[pkey]=0 end,
		upd=function(p)
			p[prop]+=cos(p[pkey])*ampl
			p[pkey]+=mag
		end
	}
end

function b_osci4(prop,mag,spd)
	local pkey="ph4_"..prop
	return{
		init=function(p) p[pkey]=0 end,
		upd=function(p)
			p[prop]+=sin(p[pkey])*mag*sgn(cos((p[pkey])/4))
			p[pkey]+=spd
		end
	}
end

function b_impulse(ang,mag)
	local a=ang/360
	return {init=function(p)
		p.vy+=sin(a)*mag
		p.vx+=cos(a)*mag
	end}
end
function up_impulse(variance,mag)
	return b_impulse(90+rnd_range(variance),mag)
end

function tweenprop(key,trgt,e,base)
	local snap=base
	return {
		init=function(p)
			if (not base) snap=p[key]
		end,
		upd=function(p)
			local prog=p:norm_life()
			if (p[key]) p[key]=tween(snap,trgt,prog,e)
		end}
end

function anim_color(ctable,easing)
	return {
		upd=function(p)
		local cindex=flr(
				tween(
				1,#ctable+1,p:norm_life(),
				easing)
			)
			p.c=ctable[cindex]
		end
	}
end

function prop_variance(key,upr,lwr)
	return {
		init=function(p)
			p[key]+=rnd_betwn(lwr or 0,upr)
		end
	}
end

-------------------------

--classes
global_scope=_𝘦𝘯𝘷

class=setmetatable({
	new=function(self,tbl)
		tbl=tbl or {}
		setmetatable(tbl,{
			__index=self
			})
		return tbl
	end
},{__index=_𝘦𝘯𝘷})

entity=class:new({x=63,y=63})

particle=entity:new({
	vx=0,vy=0,
	age=0,l=30,
	behav={},
	callback=nil,
	init=function(_𝘦𝘯𝘷)
		for b in all(behav) do
			if (b.init) b.init(_𝘦𝘯𝘷)
		end
		x+=xoff
		y+=yoff
	end,
	draw=function(_𝘦𝘯𝘷)
		for b in all(behav) do
			if (b.drw) b.drw(_𝘦𝘯𝘷)
			fillp()
		end
	end,
	update=function(_𝘦𝘯𝘷)
		for b in all(behav) do
			if (b.upd) b.upd(_𝘦𝘯𝘷)
		end
		x+=vx
		y+=vy
		age+=1
		if age>=l and callback then
			callback(_𝘦𝘯𝘷)
			callback=false
		end
		if (age>=l) del(particles,_𝘦𝘯𝘷)
	end,
	s_age=function(_𝘦𝘯𝘷,newage,life)
		age=newage
		if (life) l=life
	end,
	norm_life=function(_𝘦𝘯𝘷)
		return age/l
	end
})

puff=particle:new({
	l=40,
	behav={
		r_circ(2,5,true),
		b_drag(0.92),
		b_osci4("vx",0.05,0.05),
		b_osci("x",0.3,0.02),
		tweenprop("r",5.1,e_outcirc)
	},
	callback=function(p)
		b=p.behav
		renderer=r_circ(5,1,false,▒)
		renderer.init(p)
		b[1]=renderer
		shrinker=tweenprop("r",1)
		shrinker.init(p)
		b[5]=shrinker
--		p:init() --𝘰𝘱𝘤𝘢𝘰 𝘤𝘢𝘴𝘰 𝘥𝘦𝘴𝘦𝘫𝘢𝘷𝘦𝘭	
		p:s_age(0,20)
 end
})

fire=particle:new({
	l=45,
	behav={
		r_circ(4,9),
		tweenprop("r",2),
		anim_color(
			{7,10,10,10,9,9,9,9,9,5,5}
		)
	}
})

crackle=particle:new({
	l=15,
	behav={
		r_circ(1,9),
		r_line(2),
		b_drag(0.9,0.9),
		prop_variance("l",10,-10),
		anim_color(
			{9,9,5,1}
		)
	}
})