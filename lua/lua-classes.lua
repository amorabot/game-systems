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
--inherits the new() method and
--the '_𝘦𝘯𝘷' metatable + being
--a simple table itself
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